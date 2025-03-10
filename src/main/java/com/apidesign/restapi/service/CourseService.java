package com.apidesign.restapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apidesign.restapi.model.CourseResource;
import org.springframework.stereotype.Service;


@Service
public class CourseService {
    
    private final Map<Integer, CourseResource> courses = new HashMap<>();
    
    // Create a course
    public CourseResource createCourse(CourseResource course) {
        courses.put(course.getCourseNumber(), course);
        return course;
    }
    
    // Get a course by number
    public CourseResource getCourseByNumber(Integer courseNumber) throws Exception {
        CourseResource course = courses.get(courseNumber);
        if (course == null) {
            throw new Exception("CourseResource not found with number: " + courseNumber);
        }
        return course;
    }
    
    // Get all courses
    public List<CourseResource> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
    
    // Update a course
    public CourseResource updateCourse(Integer courseNumber, CourseResource updatedCourse) throws Exception {
        if (!courses.containsKey(courseNumber)) {
            throw new Exception("CourseResource not found with number: " + courseNumber);
        }
        updatedCourse.setCourseNumber(courseNumber);
        courses.put(courseNumber, updatedCourse);
        return updatedCourse;
    }
    
    // Delete a course
    public void deleteCourse(Integer courseNumber) throws Exception {
        if (!courses.containsKey(courseNumber)) {
            throw new Exception("CourseResource not found with number: " + courseNumber);
        }
        courses.remove(courseNumber);
    }
    
    // Check if a course exists
    public boolean doesCourseExist(Integer courseNumber) {
        return courses.containsKey(courseNumber);
    }
}
