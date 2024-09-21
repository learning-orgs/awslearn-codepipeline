package com.aws.awslearncodepipeline.controller;

import com.aws.awslearncodepipeline.model.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/students")
@Tag(name = "Student Management", description = "CRUD operations for managing students") // Tag for Swagger
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class); // Logger

    private List<Student> students = new ArrayList<>(
            List.of(
                    new Student(1, "Navin", 60),
                    new Student(2, "Kiran", 65)
            )
    );

    @Operation(summary = "Create a new student", description = "Adds a new student to the list")
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        logger.info("Request to create student: {}", student);
        students.add(student);
        logger.info("Student created successfully: {}", student);
        return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Get all students", description = "Fetches all students from the list")
    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("Fetching all students");
        return students;
    }

    @Operation(summary = "Get a student by ID", description = "Fetches a single student by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid student ID provided");
        }
        Optional<Student> student = students.stream().filter(s -> s.getId() == id).findFirst();
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @Operation(summary = "Update a student", description = "Updates an existing student's information")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        logger.info("Request to update student with ID: {}", id);
        Optional<Student> existingStudent = students.stream().filter(s -> s.getId() == id).findFirst();
        if (existingStudent.isPresent()) {
            existingStudent.get().setName(updatedStudent.getName());
            existingStudent.get().setMarks(updatedStudent.getMarks());
            logger.info("Student updated successfully: {}", existingStudent.get());
            return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
        } else {
            logger.warn("Student with ID {} not found", id);
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a student", description = "Deletes a student by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        logger.info("Request to delete student with ID: {}", id);
        Optional<Student> student = students.stream().filter(s -> s.getId() == id).findFirst();
        if (student.isPresent()) {
            students.remove(student.get());
            logger.info("Student deleted successfully with ID: {}", id);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } else {
            logger.warn("Student with ID {} not found", id);
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }
}
