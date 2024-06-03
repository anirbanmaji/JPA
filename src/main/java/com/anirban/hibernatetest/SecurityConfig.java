package com.anirban.hibernatetest;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/v1/book/admin/").hasRole("ADMIN");
                    registry.requestMatchers("/v1/book/user/").hasRole("USER");
                    registry.requestMatchers("/v1/book/").permitAll();
                    registry.anyRequest().authenticated();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails adminDetails = User.builder()
                .username("anirban")
                .password("$2a$12$9aeiePN1rOv.FgxfMkD4Xe4YFXZyYh8zv87cedpa1Z0fH0yJtNR/u")
                .roles("ADMIN", "USER")
                .build();

        UserDetails userDetails = User.builder()
                .username("baban")
                .password("$2a$12$9aeiePN1rOv.FgxfMkD4Xe4YFXZyYh8zv87cedpa1Z0fH0yJtNR/u")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(adminDetails, userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
