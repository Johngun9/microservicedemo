package com.example.microservicedemo.utils;

import com.example.microservicedemo.dto.StudentDTO;
import com.example.microservicedemo.models.Student;

public class StudentUtil {

    private StudentUtil() { }

    public static Student prepareStudentDTO(StudentDTO studentDTO){

        Student student = new Student();
        if(null != studentDTO.getId()) {
            student.setId(studentDTO.getId());
        }
        if(null != studentDTO.getName()){
            student.setName(studentDTO.getName());
        }
        if(null != studentDTO.getPassportNumber()){
            student.setPassportNumber(studentDTO.getPassportNumber());
        }

        return student;
    }
}
