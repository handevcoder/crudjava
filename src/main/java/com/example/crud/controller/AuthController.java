package com.example.crud.controller;

import com.example.crud.exception.TokenException;
import com.example.crud.model.ERole;
import com.example.crud.model.TokenRequest;
import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.model.request.LoginRequest;
import com.example.crud.model.request.SignupRequest;
import com.example.crud.model.request.TokenRefresh;
import com.example.crud.model.response.JwtResponse;
import com.example.crud.model.response.MessageResponse;
import com.example.crud.model.response.TokenResponse;
import com.example.crud.repository.RoleRepository;
import com.example.crud.repository.UserRepository;
import com.example.crud.security.UserDetailsImpl;
import com.example.crud.service.TokenService;
import com.example.crud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TokenService tokenSvc;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            if (userRepository.existsByUsername(signupRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }

            if (userRepository.existsByEmail(signupRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use"));
            }

            User user = new User(
                            signupRequest.getUsername(),
                            signupRequest.getEmail(),
                            encoder.encode(signupRequest.getPassword()));

            Set<String> strRoles = signupRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole =
                        roleRepository
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(
                                        () -> new RuntimeException("Error: Role is not Found"));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole =
                                    roleRepository
                                            .findByName(ERole.ROLE_ADMIN)
                                            .orElseThrow(
                                                    () -> new RuntimeException("Error: Role ADMIN is not found."));
                            roles.add(adminRole);
                            break;
                        case "super":
                            Role superRole =
                                    roleRepository
                                            .findByName(ERole.ROLE_SUPER)
                                            .orElseThrow(
                                                    () -> new RuntimeException("Error: Role SUPER is not found."));
                            roles.add(superRole);
                            break;
                        default:
                            Role userRole =
                                    roleRepository
                                            .findByName(ERole.ROLE_USER)
                                            .orElseThrow(
                                                    () -> new RuntimeException("Error: Role USER is not found."));
                            roles.add(userRole);
                            break;
                    }
                });
            }
            user.setRole(roles);
            userRepository.save(user);

            return ResponseEntity
                    .ok(new MessageResponse("User registered Succesfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .ok(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(),
                                    loginRequest.getPassword()
                            )
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            //String jwt = jwtUtils.generateJwtToken(userDetails);
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

            List<String> roles = userDetails
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            TokenRequest refreshToken = tokenSvc.createRefreshToken(userDetails.getId());

            Cookie sessionCookie = new Cookie("HanifSessionId", refreshToken.getToken());

            //response.addCookie(sessionCookie);

            /*return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, refreshToken.getToken().toString())
                    .body(new
                            JwtResponse(jwt,
                            refreshToken.getToken(),
                            userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(), roles));
*/

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(
                            new JwtResponse(jwtCookie.toString(),
                                    refreshToken.getToken(),
                                    userDetails.getId(),
                                    userDetails.getUsername(),
                                    userDetails.getEmail(),
                                    roles
                            )
                    );
                            /*new
                            JwtResponse(jwt,
                            refreshToken.getToken(),
                            userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(), roles));*/

        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse(e.getMessage()));
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefresh request) {
        try {
            String requestToken = request.getRefreshToken();

            return tokenSvc.findByToken(requestToken)
                    .map(tokenSvc::verifyExpiration)
                    .map(TokenRequest::getUser)
                    .map(user -> {
                        String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                        return ResponseEntity.ok(new TokenResponse(token, requestToken));
                    }).orElseThrow(() -> new TokenException(requestToken, "Refresh token is not in database!"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut() {
        try {
            ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
            return ResponseEntity
                    .ok().
                    header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(new MessageResponse("You've been signed out!"));
        } catch (Exception e) {
            return ResponseEntity
                    .ok(new MessageResponse(e.getMessage()));
        }
    }

//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(new MessageResponse("You've been signed out!"));
//    }

}
