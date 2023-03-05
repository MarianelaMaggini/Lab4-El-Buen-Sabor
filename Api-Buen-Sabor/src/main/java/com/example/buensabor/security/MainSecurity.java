package com.example.buensabor.security;

import com.example.buensabor.security.jwt.JwtEntryPoint;
import com.example.buensabor.security.jwt.JwtTokenFilter;
import com.example.buensabor.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtEntryPoint jwtEntryPoint;
    private final PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    private final JwtTokenFilter jwtTokenFilter;

    @Autowired
    public MainSecurity(UserDetailsServiceImpl userDetailsService, JwtEntryPoint jwtEntryPoint, PasswordEncoder passwordEncoder, JwtTokenFilter jwtTokenFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtEntryPoint = jwtEntryPoint;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        httpSecurity.csrf().disable();
        httpSecurity.cors();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/auth/**",
                        "/articulos/**",
                        "/rubros/**",
                        "/account/**",
                        "/webjars/**",
                        "/ws/**",
                        "/upload/**",
                        "/v2/api-docs",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/configuration/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
