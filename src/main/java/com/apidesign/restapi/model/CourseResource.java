package com.apidesign.restapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseResource {
    
    @NotNull(message = "Course number is required")
    private Integer courseNumber;
    
    @NotBlank(message = "Course title is required")
    private String courseTitle;
    
    // Default constructor
    public CourseResource() {
    }
    
    // Constructor with all fields
    public CourseResource(Integer courseNumber, String courseTitle) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
    }
    
    // Getters and Setters
    public Integer getCourseNumber() {
        return courseNumber;
    }
    
    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }
    
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
