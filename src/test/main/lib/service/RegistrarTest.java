package main.lib.service;

import main.lib.entity.Course;
import main.lib.entity.Student;
import main.lib.exception.EnrollmentException;
import main.lib.service.Registrar;
import main.lib.service.RegistrarImpl;
import org.junit.Test;

import java.util.Set;

import static main.Constants.CALENDAR;
import static org.junit.Assert.*;

public class RegistrarTest {
    private Registrar registrar = new RegistrarImpl();

    @Test
    public void registerCourse_registrarContainsCourse() {
        Course course = new Course("SWENG", CALENDAR);
        registrar.registerCourse(course);
        assertTrue(registrar.allCourses().contains(course));
    }

    @Test
    public void registerStudent_registrarContainsStudent() {
        Student student = new Student("Aiden");
        registrar.registerStudent(student);
        assert(registrar.allStudents().contains(student));
    }

    @Test
    public void coursesForStudent() throws EnrollmentException {
        Student student = new Student("Aiden");
        Course course = new Course("CMPSC", CALENDAR);
        registrar.registerStudent(student);
        registrar.registerCourse(course);
        registrar.enroll(course, student);
        Set<Course> courses = student.courses();
        assertEquals(1, courses.size());
        assertTrue(student.courses().contains(course));
    }

    @Test(expected = EnrollmentException.class)
    public void enrollStudent_studentAlreadyHasClassAtSameTime() throws EnrollmentException {
        Course course1 = new Course("SWENG", CALENDAR);
        Course course2 = new Course("CMPSC", CALENDAR);
        Student student = new Student("Aiden");
        registrar.registerCourse(course1);
        registrar.registerCourse(course2);
        registrar.registerStudent(student);
        registrar.enroll(course1, student);
        registrar.enroll(course2, student);
    }

    @Test(expected = EnrollmentException.class)
    public void enrollStudent_courseIsNotRegistered() throws EnrollmentException {
        Course course = new Course("SWENG", CALENDAR);
        Student student = new Student("Aiden");
        registrar.registerStudent(student);
        registrar.enroll(course, student);
    }

    @Test(expected = EnrollmentException.class)
    public void enrollStudent_studentIsNotRegistered() throws EnrollmentException {
        Course course = new Course("SWENG", CALENDAR);
        Student student = new Student("Aiden");
        registrar.registerCourse(course);
        registrar.enroll(course, student);
    }

}