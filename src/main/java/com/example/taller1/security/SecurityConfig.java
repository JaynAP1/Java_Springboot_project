package com.example.taller1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    
    @Value("${allowed.origins:*}")
    private String allowedOrigins;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/login").permitAll() // Explicitly permit login endpoint first
                    .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/auth/login").permitAll() // Explicitly permit OPTIONS for login
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/usuarios/exists/**").permitAll()
                    .requestMatchers("/usuarios/save-info").permitAll()
                    .requestMatchers("/save-info").permitAll()
                    .requestMatchers("/reservas").permitAll()
                    .requestMatchers("/actuator/health/**").permitAll() // Permit health checks for Railway
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow requests from configured origins
        if ("*".equals(allowedOrigins)) {
            configuration.addAllowedOriginPattern("*"); // Allow all origins with pattern
        } else {
            // Split comma-separated origins and add them
            Arrays.stream(allowedOrigins.split(","))
                  .map(String::trim)
                  .forEach(configuration::addAllowedOrigin);
        }
        // Include all necessary HTTP methods including OPTIONS for preflight
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        // Set allow credentials to true to ensure cookies/auth headers are included
        configuration.setAllowCredentials(true);
        // Explicitly list important headers including Authorization
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type", 
            "Accept", 
            "Origin", 
            "X-Requested-With", 
            "Access-Control-Request-Method", 
            "Access-Control-Request-Headers"
        ));
        // Expose response headers that might be needed by the client
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization",
            "Content-Disposition",
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials"
        ));
        // Keep credentials handling
        configuration.setAllowCredentials(true);
        // Keep existing max age
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        // Create a specific configuration for login with maximum permissiveness
        CorsConfiguration loginConfig = new CorsConfiguration();
        // Use the same origin configuration for login
        if ("*".equals(allowedOrigins)) {
            loginConfig.addAllowedOriginPattern("*"); // Allow all origins with pattern
        } else {
            // Split comma-separated origins and add them
            Arrays.stream(allowedOrigins.split(","))
                  .map(String::trim)
                  .forEach(loginConfig::addAllowedOrigin);
        }
        loginConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        loginConfig.setAllowedHeaders(Arrays.asList("*"));
        loginConfig.setExposedHeaders(Arrays.asList("*"));
        loginConfig.setAllowCredentials(true);
        loginConfig.setMaxAge(3600L);
        
        // Explicitly register configuration for auth endpoints to ensure proper CORS handling
        source.registerCorsConfiguration("/auth/login", loginConfig);
        source.registerCorsConfiguration("/auth/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

