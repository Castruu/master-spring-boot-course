package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*
        Cross-Site Request Forgery
        http.csrf().disable()...
        http.csrf().ignoringAntMatchers("/saveMsg")...
         */

        http.csrf().ignoringAntMatchers("/saveMsg", "/public/**", "/api/**", "/data-api/**", "/eschool/actuator/**")
                .and()
                .authorizeHttpRequests(auth ->
                auth.mvcMatchers("/dashboard", "/api/**", "/data-api/**").authenticated()
                        .mvcMatchers("/displayMessages", "/admin/**", "/eschool/actuator/**").hasRole("ADMIN")
                        .mvcMatchers("/student/**").hasRole("STUDENT")
                        .mvcMatchers(
                        "/courses", "/home",
                        "/contact", "/holidays/**", "/saveMsg", "/about", "/login", "/public/**")
                        .permitAll()
                )
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true).permitAll()
                .and()
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
