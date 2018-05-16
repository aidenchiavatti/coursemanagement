package main.lib;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RegistrarImpl implements Registrar {
    private HashSet<Course> courses = new HashSet<>();
    private HashSet<Student> students = new HashSet<>();

    @Override
    public void registerCourse(Course course) {
        courses.add(course);
    }

    @Override
    public void registerStudent(Student student) {
        students.add(student);
    }

    @Override
    public HashSet<Course> allCourses() {
        return courses;
    }

    @Override
    public HashSet<Student> allStudents() {
        return students;
    }

    @Override
    public void enroll(Course course, Student student) throws EnrollmentException {
        if(!courses.contains(course)) {
            throw new EnrollmentException("Course has not been registered.");
        } else if(!students.contains(student)) {
            throw new EnrollmentException("Student has not been registered.");
        }
        for(Course c : student.courses()) {
            if(c.getTime().getTime().getTime() == course.getTime().getTime().getTime()) {
                throw new EnrollmentException("Student already has class at this time.");
            }
        }
        course.enroll(student);
        student.enroll(course);
    }
}
