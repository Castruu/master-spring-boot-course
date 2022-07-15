package com.example.spring.service;

import com.example.spring.model.ESClass;
import com.example.spring.model.Person;
import com.example.spring.repository.ESClassRepository;
import com.example.spring.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ESClassService {

    private final ESClassRepository classRepository;

    private final PersonRepository personRepository;

    @Autowired
    public ESClassService(ESClassRepository classRepository, PersonRepository personRepository) {
        this.classRepository = classRepository;
        this.personRepository = personRepository;
    }

    public boolean createNewClass(ESClass esClass) {
        classRepository.save(esClass);
        return esClass.getClassId() > 0;
    }

    public ESClass getClassById(int id) {
        return classRepository.findById(id).orElseThrow();
    }

    public List<ESClass> getAllClasses() {
        return classRepository.findAll();
    }

    public void removeStudentsAndDeleteClassById(int id) {
        ESClass esClass = classRepository.findById(id).orElseThrow();
        for(Person person : esClass.getPersons()) {
            person.setEsClass(null);
            personRepository.save(person);
        }
        classRepository.delete(esClass);
    }

    public String addStudent(ESClass esClass, Person person) {
        Person storedPerson = personRepository.findByEmail(person.getEmail());
        if(storedPerson == null || storedPerson.getPersonId() <= 0) return "redirect:/admin/displayStudents?classId=" + esClass.getClassId() + "&error=true";
        storedPerson.setEsClass(esClass);
        personRepository.save(storedPerson);
        esClass.getPersons().add(storedPerson);
        classRepository.save(esClass);
        return "redirect:/admin/displayStudents?classId=" + esClass.getClassId();
    }

    public ESClass deleteStudent(ESClass esClass, int id) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setEsClass(null);
        personRepository.save(person);
        esClass.getPersons().remove(person);
        esClass = classRepository.save(esClass);
        return esClass;
    }
}
