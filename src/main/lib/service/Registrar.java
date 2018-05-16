package main.lib.service;

import main.lib.entity.Course;
import main.lib.entity.Student;
import main.lib.exception.EnrollmentException;

import java.util.Set;

public interface Registrar {
    void registerCourse(Course course);

    void registerStudent(Student student);

    void enroll(Course course, Student student) throws EnrollmentException;

    Set<Course> allCourses();

    Set<Student> allStudents();
}
