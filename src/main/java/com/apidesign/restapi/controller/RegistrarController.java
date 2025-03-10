package com.apidesign.restapi.controller;

import java.util.List;

import com.apidesign.restapi.model.StudentResource;
import com.apidesign.restapi.service.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/registrar")
public class RegistrarController {
    
    @Autowired
    private RegistrarService registrarService;
    
    // UC_R1: Register a student to a course
    @PostMapping("/courses/{courseNumber}/students/{studentId}")
    public ResponseEntity<Void> registerStudentToCourse(@PathVariable Integer courseNumber, 
                                                      @PathVariable Integer studentId) throws Exception {
        registrarService.registerStudentToCourse(courseNumber, studentId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    // UC_R2: Get all students registered to a course
    @GetMapping("/courses/{courseNumber}/students")
    public ResponseEntity<List<StudentResource>> getStudentsForCourse(@PathVariable Integer courseNumber) throws Exception {
        List<StudentResource> students = registrarService.getStudentsForCourse(courseNumber);
        return ResponseEntity.ok(students);
    }
    
    // UC_R3: Drop a student from a course
    @DeleteMapping("/courses/{courseNumber}/students/{studentId}")
    public ResponseEntity<Void> dropStudentFromCourse(@PathVariable Integer courseNumber, 
                                                    @PathVariable Integer studentId) throws Exception {
        registrarService.dropStudentFromCourse(courseNumber, studentId);
        return ResponseEntity.noContent().build();
    }
}
