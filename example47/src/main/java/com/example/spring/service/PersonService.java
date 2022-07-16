package com.example.spring.service;

import com.example.spring.constants.ESConstants;
import com.example.spring.model.Person;
import com.example.spring.model.Roles;
import com.example.spring.repository.PersonRepository;
import com.example.spring.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    private final RolesRepository rolesRepository;

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createNewUser(Person person) {
        boolean isSaved;
        Roles roles = rolesRepository.findByRoleName(ESConstants.STUDENT_ROLE);
        person.setRoles(roles);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        isSaved = person.getPersonId() > 0;
        return isSaved;
    }



}
