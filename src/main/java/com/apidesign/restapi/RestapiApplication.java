package com.apidesign.restapi;

import com.apidesign.restapi.model.CourseResource;
import com.apidesign.restapi.model.StudentResource;
import com.apidesign.restapi.service.CourseService;
import com.apidesign.restapi.service.RegistrarService;
import com.apidesign.restapi.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@SpringBootApplication
@ComponentScan
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(StudentService studentService, CourseService courseService,
									  RegistrarService registrarService) {
		return args -> {
			// Add sample students
			StudentResource student1 = new StudentResource(1001, "John", "Doe", LocalDate.of(1995, 5, 15), "john.doe@example.com");
			StudentResource student2 = new StudentResource(1002, "Jane", "Smith", LocalDate.of(1997, 8, 22), "jane.smith@example.com");
			StudentResource student3 = new StudentResource(1003, "Michael", "Johnson", LocalDate.of(1996, 3, 10), "michael.j@example.com");

			studentService.createStudent(student1);
			studentService.createStudent(student2);
			studentService.createStudent(student3);

			// Add sample courses
			CourseResource course1 = new CourseResource(101, "Introduction to Computer Science");
			CourseResource course2 = new CourseResource(102, "Data Structures and Algorithms");
			CourseResource course3 = new CourseResource(103, "Web Development");

			courseService.createCourse(course1);
			courseService.createCourse(course2);
			courseService.createCourse(course3);

			// Register students to courses
			registrarService.registerStudentToCourse(101, 1001);
			registrarService.registerStudentToCourse(101, 1002);
			registrarService.registerStudentToCourse(102, 1001);
			registrarService.registerStudentToCourse(102, 1003);
			registrarService.registerStudentToCourse(103, 1002);
			registrarService.registerStudentToCourse(103, 1003);

			System.out.println("Sample data has been loaded.");
		};

	}
}
