package com.apidesign.restapi.controller;

import java.util.List;

import com.apidesign.restapi.model.CourseResource;
import com.apidesign.restapi.service.CourseService;
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


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    // UC_C1: Create a course
    @PostMapping
    public ResponseEntity<CourseResource> createCourse(@Valid @RequestBody CourseResource course) {
        CourseResource createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }
    
    // UC_C2: Get course by number
    @GetMapping("/{courseNumber}")
    public ResponseEntity<CourseResource> getCourseByNumber(@PathVariable Integer courseNumber) throws Exception {
        CourseResource course = courseService.getCourseByNumber(courseNumber);
        return ResponseEntity.ok(course);
    }
    
    // UC_C3: Get all courses
    @GetMapping
    public ResponseEntity<List<CourseResource>> getAllCourses() {
        List<CourseResource> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    // UC_C4: Update course
    @PutMapping("/{courseNumber}")
    public ResponseEntity<CourseResource> updateCourse(@PathVariable Integer courseNumber, 
                                             @Valid @RequestBody CourseResource course) throws Exception {
        CourseResource updatedCourse = courseService.updateCourse(courseNumber, course);
        return ResponseEntity.ok(updatedCourse);
    }
    
    // UC_C5: Delete course
    @DeleteMapping("/{courseNumber}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer courseNumber) throws Exception {
        courseService.deleteCourse(courseNumber);
        return ResponseEntity.noContent().build();
    }
}
