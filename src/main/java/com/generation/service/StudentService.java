package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentService
{
    // Implementation of Map interface - approvedCourses with a signature(key-value pair) of String studentId, Student student
    private final Map<String, Student> students = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )
    {
        //TODO implement this method
        //subscribeStudent has added the studentId as the key in the HashMap students, so by checking if students contain key of studentId, we will know if the student isSubscribed
        if(students.containsKey(studentId)){
            return true;
        } return false;
    }

    public void showSummary()
    {
        //TODO implement - show summary of students who have already enrolled
        //Display the list of students and basic information
        System.out.println("Students: ");
        for(String key : students.keySet()){
            Student student = students.get(key);
            System.out.println(student);
        }

        //Get user input on which student would they like to inspect further through studentId
        //Display the grades of the respective graded courses
        System.out.println("Enter student ID to view grades and passed courses: ");
        String studentId = scanner.nextLine();

        Student student = findStudent(studentId);
        if(student != null) {
            System.out.println("Student ID: " + studentId);
            System.out.println("Student Name: " + student.getName());

            Map<String, Integer> courseGrades = student.getCourseGrades();

            if (courseGrades.isEmpty()) {
                System.out.println("No grades available.");
            } else {
                System.out.println("Course grades: ");
                for (Map.Entry<String, Integer> entry : courseGrades.entrySet()) {
                    String courseCode = entry.getKey();
                    int grade = entry.getValue();
                    System.out.println("Course: " + courseCode + " | Grade: " + grade);
                }
            }

            List<Course> courses = student.findPassedCourses(6);
            if(!courses.isEmpty()){
                System.out.println("Passed Courses: ");
                for (Course course : courses){
                    System.out.println("Course: " + course.getCode() + " | Course name: " + course.getName());
                }
            } else{
                System.out.println("No passed courses.");
            }

        } else {
            System.out.println("Student ID: " + studentId + " not found.");
        }
    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            students.get( studentId ).enrollToCourse( course );
        }
    }


}
