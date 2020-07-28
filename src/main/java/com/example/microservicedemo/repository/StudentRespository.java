package com.example.microservicedemo.repository;

import com.example.microservicedemo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {
}
