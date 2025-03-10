package com.apidesign.restapi.model;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrarResource {
    
    @NotNull(message = "Course number is required")
    private Integer courseNumber;
    
    @Size(max = 15, message = "Maximum number of students per course is 15")
    private List<Integer> studentIds;
    
    // Default constructor
    public RegistrarResource() {
    }
    
    // Constructor with all fields
    public RegistrarResource(Integer courseNumber, List<Integer> studentIds) {
        this.courseNumber = courseNumber;
        this.studentIds = studentIds;
    }
    
    // Getters and Setters
    public Integer getCourseNumber() {
        return courseNumber;
    }
    
    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }
    
    public List<Integer> getStudentIds() {
        return studentIds;
    }
    
    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }
}
