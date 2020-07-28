package com.example.microservicedemo.controller;

import com.example.microservicedemo.models.Student;
import com.example.microservicedemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@RestController
public class Home {
    @Autowired
    StudentService studentService;

    @GetMapping("/home")
    public String getHomeGreeting(){
        return "Greeting from controller";
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
    public Student saveStudent(@RequestBody Student student){
       return studentService.save(student);
    }
    @PutMapping("/updateStudent")
    public void updateStudent(@RequestBody Student student){
       Optional<Student> student1 = studentService.getStudent(student.getId());
       Student s1 = new Student();
        if(student1.isPresent()){
            s1.setId(student.getId());
            s1.setName(student.getName());
            s1.setPassportNumber(student.getPassportNumber());
            studentService.save(s1);
        }else {
            throw new ResourceAccessException("This is resource is not available");
        }

    }
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id){

            studentService.deleteStudent(id);

    }
}
