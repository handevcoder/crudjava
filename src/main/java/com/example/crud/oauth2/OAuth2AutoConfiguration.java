//package com.example.crud.oauth2;

//import com.example.crud.model.ERole;

//import com.example.crud.security.AuthEntryPointJwt;
//import com.example.crud.security.AuthTokenFilter;
//import com.example.crud.security.UserDetailsServiceImpl;

//import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class OAuth2AutoConfiguration {

    //@Autowired
   // UserDetailsServiceImpl userDetailsService;

   // @Autowired
   // private AuthEntryPointJwt unauthorizedHandler;

    //@Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }
//}


/*

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String a = ERole.USER.toString();
        String b = ERole.ADMIN.toString();
        String c = ERole.SUPER.toString();

        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_USER)
                .hasRole(a)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ADMIN)
                .hasRole(b)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_SUPER)
                .hasRole(c)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_SUPER)
                .hasRole(a)
                .anyRequest()
                .authenticated();

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        *//*to enable h2-console user *//*
        http.headers().frameOptions().disable();

        return http.build();
    }

    private static final String[] AUTH_SUPER = {
            *//* Scope API *//*
            "/api/auth/**",
            "/api/test/**",
            *//* Swagger UI v2 *//*
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            *//* Database-H2 *//*
            "/h2-console/**",
    };
    private static final String[] AUTH_ADMIN = {
            *//* Scope API *//*
            "/api/auth/**",
            "/api/test/**",
            *//* Swagger UI v2 *//*
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            *//* Database-H2 *//*
            "/h2-console/**",
    };
    private static final String[] AUTH_USER = {
            *//* Scope API *//*
            "/api/auth/**",
            "/api/test/**",
            *//* Swagger UI v2 *//*
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            *//* Database-H2 *//*
            "/h2-console/**",
    };
*/