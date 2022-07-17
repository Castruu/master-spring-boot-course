package com.example.spring.repository;

import com.example.spring.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "courses"
/*, exported = false*/
)
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    List<Courses> findByOrderByName();

    List<Courses> findByOrderByNameDesc();
}
