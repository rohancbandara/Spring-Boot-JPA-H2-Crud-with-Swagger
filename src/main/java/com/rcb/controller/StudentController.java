package com.rcb.controller;

import com.rcb.dto.StudentInsertDTO;
import com.rcb.dto.StudentResponseDTO;
import com.rcb.service.StudentService;
import com.rcb.utill.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-by-status/{status}")
    public List<StudentResponseDTO> getStudentsByStaÁtus(@PathVariable("status") String status) {
        return studentService.getByStatus(status);
    }

    @PostMapping("/save-update")
    public CommonResponse saveUpdateStudent(@RequestBody StudentInsertDTO dto) {
        return studentService.saveUpdateStudent(dto);
    }

    @DeleteMapping("/{studentId}")
    public CommonResponse deleteStudent(@PathVariable("studentId") String studentId) {
        return studentService.deleteById(studentId);
    }

    @GetMapping("/{studentId}")
    public StudentResponseDTO getStudentById(@PathVariable("studentId") String studentId) {
        return studentService.getStudentById(studentId);
    }
}
