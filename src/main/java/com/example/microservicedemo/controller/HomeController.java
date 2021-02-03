package com.example.microservicedemo.controller;

import com.example.microservicedemo.dto.StudentDTO;
import com.example.microservicedemo.models.Student;
import com.example.microservicedemo.service.StudentService;
import com.example.microservicedemo.utils.StudentUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    private StudentService studentService;

    public HomeController(StudentService studentService) {

        Assert.notNull(studentService, "StudentService may not be null");
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String getHomeGreeting(){
        return "Hi, Welcome to Microservices Architechure";
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }

    @PostMapping("/student")
    public Student saveStudent(@RequestBody StudentDTO studentDTO){
        Student student = StudentUtil.prepareStudentDTO(studentDTO);
        return studentService.save(student);
    }

    @PutMapping("/updateStudent")
    public void updateStudent(@RequestBody StudentDTO studentDTO) {
        Optional<Student> studentOptional = studentService.getStudent(studentDTO.getId());
        if (studentOptional.isPresent()) {
            Student student = StudentUtil.prepareStudentDTO(studentDTO);
            studentService.save(student);
        } else {
            throw new ResourceAccessException("This is resource is not available");
        }
    }
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
}
