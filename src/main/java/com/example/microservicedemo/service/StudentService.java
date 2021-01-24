package com.example.microservicedemo.service;

import com.example.microservicedemo.models.Student;
import com.example.microservicedemo.repository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRespository studentRespository;

    public Optional<Student> getStudent(Long id){
        return studentRespository.findById(id);
    }

    public Student save(Student student) {
        return studentRespository.save(student);
    }

    public List<Student> getAllStudents() {
       return studentRespository.findAll();
    }

    public void deleteStudent(Long delstudent) {
        studentRespository.deleteById(delstudent);
    }
}
