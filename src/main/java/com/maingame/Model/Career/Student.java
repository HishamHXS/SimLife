package com.maingame.Model.Career;


public class Student extends Career {

    public enum StudentType {
        HIGHSCHOOL,
        MIDDLESCHOOL,
        ELEMENTARYSCHOOL,
        COLLEGE
    }

    private final String degree;
    private final String schoolName;
    private StudentType studentType;

    public Student(String degree, StudentType studentType, String schoolName) {
        super("Student");
        this.degree = degree;
        this.schoolName = schoolName;
        this.studentType = studentType;
   }

    public String getDegree() {
        return this.degree;
    }

    @Override
    public String getInstitutionName() {
        return this.schoolName;
    }

    @Override
    public String getDetails() {
        return "Student | Degree: " + degree + " | Study Hours: " + 40 + " per week";
    }
}
