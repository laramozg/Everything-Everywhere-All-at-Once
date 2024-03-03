package com.example.db.security;

import com.example.db.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtTokenFilter jwtAuthFilter;

    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:8080"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        return corsConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfiguration corsConfiguration) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(config -> config.configurationSource(source -> corsConfiguration))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "/**"
                                ).permitAll()

                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /*
    .requestMatchers("/profile", "/profile/**").hasAnyAuthority("ROLE_Герой", "ROLE_Координатор")
                                .requestMatchers("/work/**").hasAnyAuthority("ROLE_Технический_специалист")
                                .anyRequest().authenticated()
     */


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
