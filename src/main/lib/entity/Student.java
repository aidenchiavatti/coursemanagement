package main.lib.entity;

import java.util.HashSet;

public class Student {
    private String name;
    private HashSet<Course> courses;

    public Student(String name) {
        this.name = name;
        this.courses = new HashSet<>();
    }

    @Override public String toString() {
        return name;
    }

    public HashSet<Course> courses() {
        return this.courses;
    }

    public void enroll(Course course) {
        courses.add(course);
    }
}
