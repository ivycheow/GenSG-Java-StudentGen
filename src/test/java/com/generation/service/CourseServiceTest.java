package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @Test
    void registerCourse() {
        CourseService courseService = new CourseService();
        Module module = new Module("TEST-MODULE", "Test Module Name", "Test Module Description");
        Course course = new Course("TEST-COURSE", "Test Course Name", 9, module);

        courseService.registerCourse(course);
        assertEquals(course, courseService.getCourse(course.getCode()));
    }

    @Test
    void enrollStudent() throws ParseException {
        //Create a stream to hold the output
        // ByteArrayOutputStream - A type of output stream that writes data to a byte array in memory
        ByteArrayOutputStream captureOutput = new ByteArrayOutputStream();
        //System.setOut - Capture the output made to console
        //new PrintStream(captureOutput) - data captured in captureOutput will be then stored in ByteArrayOutputStream
        System.setOut(new PrintStream(captureOutput));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        CourseService courseService = new CourseService();
        Module module = new Module("TEST-MODULE", "Test Module Name", "Test Module Description");
        Course course = new Course("TEST-COURSE", "Test Course Name", 9, module);
        Student student = new Student("001", "Ivy", "ivy@gmail.com", dateFormat.parse("01/01/1999"));

        courseService.registerCourse(course);
        courseService.enrollStudent(course.getCode(), student);
        courseService.showEnrolledStudents(course.getCode());

        String output = captureOutput.toString();
        assertTrue(output.contains(student.getName()));
    }
}