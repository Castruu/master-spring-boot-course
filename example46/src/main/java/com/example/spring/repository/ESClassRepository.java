package com.example.spring.repository;

import com.example.spring.model.ESClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESClassRepository extends JpaRepository<ESClass, Integer> {



}
