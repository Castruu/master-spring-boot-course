package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

        http.authorizeHttpRequests(auth ->
                auth.mvcMatchers("dashboard").authenticated()
                        .mvcMatchers(
                        "/courses", "/home",
                        "/contact", "/holidays/**", "/saveMsg", "about", "login")
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
    public InMemoryUserDetailsManager useDetailsService() {
        /* Another way
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
               UserDetails user = User.withDefaultPasswordEncoder()
                .username("user").password("12345").build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin").build();
                userDetailsService.createUser(admin);
                userDetailsService.createUser(user);
         return userDetailsService;
         */
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user").password("12345")
                .roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin")
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(admin, user);

    }

}
