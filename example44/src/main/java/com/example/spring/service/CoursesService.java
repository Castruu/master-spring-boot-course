package com.example.spring.service;

import com.example.spring.model.Courses;
import com.example.spring.model.Person;
import com.example.spring.repository.CoursesRepository;
import com.example.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    private final CoursesRepository coursesRepository;

    private final PersonRepository personRepository;

    @Autowired
    public CoursesService(CoursesRepository coursesRepository, PersonRepository personRepository) {
        this.coursesRepository = coursesRepository;
        this.personRepository = personRepository;
    }

    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    public void save(Courses course) {
        coursesRepository.save(course);
    }

    public Courses getById(int id) {
        return coursesRepository.findById(id).orElseThrow();
    }

    public boolean addStudentToCourse(Courses courses, String email) {
        Person personEntity = personRepository.findByEmail(email);
        if(personEntity == null || personEntity.getPersonId() <= 0) return false;
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        return true;
    }

    public void deleteStudentFromCourse(Courses courses, int personId) {
        Person person = personRepository.findById(personId).orElseThrow();
        person.getCourses().remove(courses);
        courses.getPersons().remove(person);
        personRepository.save(person);
    }

}
