package main.lib.entity;

import main.lib.entity.Course;
import main.lib.entity.Room;
import main.lib.entity.Student;
import org.junit.Test;

import static main.Constants.CALENDAR;
import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void students_noStudentsInNewCourse() {
        Course course = new Course("SWENG", CALENDAR);
        assertEquals(0, course.students().size());
    }

    @Test
    public void enroll_studentIsShownInClass() {
        Course course = new Course("SWENG", CALENDAR);
        Student student = new Student("Aiden");
        course.enroll(student);
        assertEquals(1, course.students().size());
        assertTrue(course.students().contains(student));
    }

    @Test
    public void print() {
        Course course = new Course("SWENG", CALENDAR);
        assertEquals("Course name: SWENG\n" +
                "Course Time: Thu Jan 01 10:10:10 EST 1970", course.toString());
    }

    @Test
    public void setRoom() {
        Room room = new Room("room1");
        Course course = new Course("SWENG", CALENDAR);
        course.setRoom(room);
        assertEquals(room, course.getRoom());
    }
}