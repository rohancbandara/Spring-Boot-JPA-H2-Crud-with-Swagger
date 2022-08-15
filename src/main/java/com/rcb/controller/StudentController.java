package com.rcb.controller;

import com.rcb.dto.StudentResponseDTO;
import com.rcb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get-all")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
}
