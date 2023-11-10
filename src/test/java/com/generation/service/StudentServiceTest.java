package com.generation.service;

import com.generation.model.Student;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void subscribeStudent() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        StudentService studentService = new StudentService();
        Student student1 = new Student("001", "Ivy", "ivy@gmail.com", dateFormat.parse("01/01/1999"));

        studentService.subscribeStudent(student1);
        assertTrue(studentService.isSubscribed(student1.getId()));
    }

    @Test
    void findStudent() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        StudentService studentService = new StudentService();
        Student student1 = new Student("001", "Ivy", "ivy@gmail.com", dateFormat.parse("01/01/1999"));

        studentService.subscribeStudent(student1);
        Student foundStudent = studentService.findStudent(student1.getId());

        assertEquals("001", foundStudent.getId());
        assertEquals("Ivy", foundStudent.getName());
    }
}