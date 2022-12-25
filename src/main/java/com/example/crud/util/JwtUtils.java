package com.example.crud.util;

import com.example.crud.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${hanif.app.jwtSecret}")
    private String jwtSecret;

    @Value("${hanif.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    @Value("${hanif.app.jwtCookie}")
    private String jwtCookie;

    @Value("${hanif.app.jwtRefreshExpirationMs}")
    private Long jwtRefreshExpirationMs;


//    public String generateJwtToken(UserDetailsImpl userPrincipal) {
//        return generateTokenFromUsername(userPrincipal.getUsername());
//    }


//    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
//        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
//        return generateCookie(jwtCookie, jwt, "/api");
//    }
//
//    public ResponseCookie generateJwtCookie(User user) {
//        String jwt = generateTokenFromUsername(user.getUsername());
//        return generateCookie(jwtCookie, jwt, "/api");
//    }
//
//    public ResponseCookie generateRefreshJwtCookie(String refreshToken) {
//        return generateJwtCookie(jwtRefreshCookie, refreshToken, "/api/auth/refreshtoken");
//    }
//    ////
//    public String getJwtFromCookies(HttpServletRequest request) {
//        return getCookieValueByName(request, jwtCookie);
//    }
//
//    public String getJwtRefreshFromCookies(HttpServletRequest request) {
//        return getCookieValueByName(request, jwtRefreshCookie);
//    }
//    ////
//    public ResponseCookie getJwtFromCookies() {
//        ResponseCookie cookie =
//                ResponseCookie
//                        .from(jwtCookie, null)
//                        .path("/api")
//                        .build();
//        return cookie;
//    }
//
//    public ResponseCookie getCleanJwtRefreshCookie() {
//        ResponseCookie cookie =
//                ResponseCookie
//                        .from(jwtRefreshCookie, null)
//                        .path("/api/auth/refreshtoken")
//                        .build();
//        return cookie;
//    }
//    public String generateTokenFromUsername(String username) {
//        return Jwts
//                .builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(new Date().getTime() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public String getUserNameFromJwtToken(String token) {
//        return Jwts
//                .parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJwt(token)
//                .getBody()
//                .getSubject();
//    }
    public boolean validateJwtToken(String token) {
        try { Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token);
            return true;
        }
        catch (SignatureException e)
        { log.error("Invalid Signature: {}", e.getMessage()); }
        catch (MalformedJwtException e)
        { log.error("Token: {}", e.getMessage()); }
        catch (ExpiredJwtException e)
        { log.error("Expired: {}", e.getMessage()); }
        catch (UnsupportedJwtException e)
        { log.error("Unsupported: {}", e.getMessage()); }
        catch (IllegalArgumentException e)
        { log.error("JWT claims string is empty: {}", e.getMessage()); }
        return false;
    }


    public String getJwtFromCookies(HttpServletRequest request) {
       Cookie cookie = WebUtils.getCookie(request, jwtCookie);
       if (cookie != null) {
           return cookie.getValue();
       } else {
           return null;
       }
    }

    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
       String jwt = generateTokenFromUsername(userPrincipal.getUsername());
       return ResponseCookie
                       .from(jwtCookie, jwt)
                       .path("/api")
                       .maxAge(jwtRefreshExpirationMs)
                       .httpOnly(true)
                       .build();

    }

    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie
                .from(jwtCookie, null)
                .path("/api")
                .build();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
