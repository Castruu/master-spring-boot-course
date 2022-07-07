package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //Cross-Site Request Forgery

        http.authorizeHttpRequests(auth ->
                auth.mvcMatchers("/courses").authenticated()
                        .mvcMatchers("/home", "/contact", "/holidays/**", "/saveMsg", "about").permitAll()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
