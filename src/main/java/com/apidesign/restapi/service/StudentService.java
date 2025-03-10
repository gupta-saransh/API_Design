package com.apidesign.restapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apidesign.restapi.model.StudentResource;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    
    private final Map<Integer, StudentResource> students = new HashMap<>();
    
    // Create a student
    public StudentResource createStudent(StudentResource student) {
        students.put(student.getStudentId(), student);
        return student;
    }
    
    // Get a student by ID
    public StudentResource getStudentById(Integer studentId) throws Exception {
        StudentResource student = students.get(studentId);
        if (student == null) {
            throw new Exception("StudentResource not found with id: " + studentId);
        }
        return student;
    }
    
    // Get all students
    public List<StudentResource> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    
    // Update a student
    public StudentResource updateStudent(Integer studentId, StudentResource updatedStudent) throws Exception {
        if (!students.containsKey(studentId)) {
            throw new Exception("StudentResource not found with id: " + studentId);
        }
        updatedStudent.setStudentId(studentId);
        students.put(studentId, updatedStudent);
        return updatedStudent;
    }
    
    // Delete a student
    public void deleteStudent(Integer studentId) throws Exception {
        if (!students.containsKey(studentId)) {
            throw new Exception("StudentResource not found with id: " + studentId);
        }
        students.remove(studentId);
    }
    
    // Check if a student exists
    public boolean doesStudentExist(Integer studentId) {
        return students.containsKey(studentId);
    }
}
