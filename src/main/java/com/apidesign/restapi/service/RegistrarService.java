package com.apidesign.restapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apidesign.restapi.model.RegistrarResource;
import com.apidesign.restapi.model.StudentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarService {
    
    private final Map<Integer, List<Integer>> courseRegistrations = new HashMap<>();
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;
    
    // Register a student to a course
    public void registerStudentToCourse(Integer courseNumber, Integer studentId) throws Exception {
        // Validate course and student exist
        if (!courseService.doesCourseExist(courseNumber)) {
            throw new Exception("Course not found with number: " + courseNumber);
        }
        
        if (!studentService.doesStudentExist(studentId)) {
            throw new Exception("Student not found with id: " + studentId);
        }
        
        // Get or create the list of students for this course
        List<Integer> studentIds = courseRegistrations.computeIfAbsent(courseNumber, k -> new ArrayList<>());
        
        // Check if student is already registered
        if (studentIds.contains(studentId)) {
            throw new Exception("Student is already registered to this course");
        }
        
        // Check if course has reached the maximum number of students (15)
        if (studentIds.size() >= 15) {
            throw new Exception("Course has reached maximum capacity of 15 students");
        }
        
        // Register the student
        studentIds.add(studentId);
    }
    
    // Get all students registered for a course
    public List<StudentResource> getStudentsForCourse(Integer courseNumber) throws Exception {
        // Validate course exists
        if (!courseService.doesCourseExist(courseNumber)) {
            throw new Exception("Course not found with number: " + courseNumber);
        }
        
        // Get the list of student IDs
        List<Integer> studentIds = courseRegistrations.getOrDefault(courseNumber, new ArrayList<>());
        
        // Convert student IDs to Student objects
            /*        return studentIds.stream()
                .map(studentService::getStudentById)
                .collect(Collectors.toList());*/
        return new ArrayList<>();
    }

    // Drop a student from a course
    public void dropStudentFromCourse(Integer courseNumber, Integer studentId) throws Exception {
        // Validate course exists
        if (!courseService.doesCourseExist(courseNumber)) {
            throw new Exception("Course not found with number: " + courseNumber);
        }
        
        // Get the list of students for this course
        List<Integer> studentIds = courseRegistrations.get(courseNumber);
        
        if (studentIds == null || !studentIds.contains(studentId)) {
            throw new Exception("Student with id " + studentId + " is not registered for course " + courseNumber);
        }
        
        // Remove the student from the course
        studentIds.remove(studentId);
    }
    
    // Get the registration data for a course
    public RegistrarResource getRegistrationDataForCourse(Integer courseNumber) throws Exception {
        // Validate course exists
        if (!courseService.doesCourseExist(courseNumber)) {
            throw new Exception("Course not found with number: " + courseNumber);
        }
        
        List<Integer> studentIds = courseRegistrations.getOrDefault(courseNumber, new ArrayList<>());
        return new RegistrarResource(courseNumber, studentIds);
    }
}
