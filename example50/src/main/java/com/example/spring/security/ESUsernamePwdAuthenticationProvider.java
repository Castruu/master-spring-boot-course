package com.example.spring.security;

import com.example.spring.model.Person;
import com.example.spring.model.Roles;
import com.example.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ESUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ESUsernamePwdAuthenticationProvider(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Person person = personRepository.findByEmail(email);
        if(person == null || person.getPersonId() <= 0 || !passwordEncoder.matches(pwd, person.getPwd()))
            throw new BadCredentialsException("Incorrect credentials");
        return new UsernamePasswordAuthenticationToken(person.getEmail(), null, getGrantedAuthorities(person.getRoles()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
        return authorities;
    }

}
