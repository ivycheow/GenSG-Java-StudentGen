package com.generation.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
    extends Person
    implements Evaluation
{
    private double average;

    // ArrayList courses
    private final List<Course> courses = new ArrayList<>();

    // Implementation of Map interface - approvedCourses with a signature(key-value pair) of String courseCode, Course course
    private final Map<String, Course> approvedCourses = new HashMap<>();

    // To store grade course summary
    private final Map<String, Integer> courseGrades = new HashMap<>();

    // Constructor method with 4 parameters; super inherited from parent class - Person
    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrollToCourse( Course course ) {
        //TODO implement this method
        if(!approvedCourses.containsKey(course.getCode())){
            registerApprovedCourse(course);
        }
    }

    public void registerApprovedCourse( Course course )
    {
        //adds courseCode(key) and course(value) into the HashMap
        approvedCourses.put( course.getCode(), course );
    }

    public boolean isCourseApproved( String courseCode )
    {
        //TODO implement this method
        //to check if the courseCode is within the approvedCourses HashMap
        return approvedCourses.containsKey(courseCode);
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve. 
    public List<Course> findPassedCourses(int passingMark) {
        for (Course course : approvedCourses.values()) {
            if (courseGrades.containsKey(course.getCode()) && courseGrades.get(course.getCode()) >= passingMark) {
                courses.add(course);
            }
        }
        return courses;
    }

    public boolean isAttendingCourse( String courseCode )
    {
        //TODO implement this method
        //to check if the courseCode is within the approvedCourses HashMap
        return approvedCourses.containsKey(courseCode);
    }

    //To assign a grade to a specific course identified by courseCode
    //If approvedCourses contain courseCode(key), it will set the grade (value) for that specific course by calling the setGrade method
    public void gradeCourse(String courseCode, int grade) {
        if (approvedCourses.containsKey(courseCode)) {
            approvedCourses.get(courseCode).setGrade(grade);

            courseGrades.put(courseCode, grade);
        }
    }

    public Map<String, Integer> getCourseGrades(){
        return courseGrades;
    }

    @Override
    public double getAverage()
    {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses()
    {
        //TODO implement this method
        //create a new ArrayList called approvedCourseList by returning the values of approvedCourses
        List<Course> approvedCourseList = new ArrayList<>(approvedCourses.values());
        return approvedCourseList;
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}
