package com.apidesign.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apidesign.restapi.service.*;
import com.apidesign.restapi.model.StudentResource;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    // UC_S1: Create a student
    @PostMapping
    public ResponseEntity<StudentResource> createStudent(@Valid @RequestBody StudentResource student) {
        StudentResource createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
    
    // UC_S2: Get student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResource> getStudentById(@PathVariable Integer studentId) throws Exception {
        StudentResource student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }
    
    // UC_S3: Get all students
    @GetMapping
    public ResponseEntity<List<StudentResource>> getAllStudents() {
        List<StudentResource> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    // UC_S4: Update student
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResource> updateStudent(@PathVariable Integer studentId, 
                                               @Valid @RequestBody StudentResource student) throws Exception {
        StudentResource updatedStudent = studentService.updateStudent(studentId, student);
        return ResponseEntity.ok(updatedStudent);
    }
    
    // UC_S5: Delete student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer studentId) throws Exception {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
