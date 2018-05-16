package lib;

import main.lib.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static main.Constants.CALENDAR;
import static org.junit.Assert.*;

public class RoomSchedulerTest {

    class RegistrarMock implements Registrar {

        @Override
        public void registerCourse(Course course) {

        }

        @Override
        public void registerStudent(Student student) {

        }

        @Override
        public void enroll(Course course, Student student) throws EnrollmentException {

        }

        @Override
        public Set<Course> allCourses() {
            Set<Course> courses = new HashSet<>();
            courses.add(COURSE);
            COURSE.setRoom(ROOM);
            return courses;
        }

        @Override
        public Set<Student> allStudents() {
            return null;
        }

        @Override
        public Set<Course> coursesFor(Student student) {
            return null;
        }
    }

    private final CourseMock COURSE = new CourseMock("SWENG", CALENDAR);
    private final Room ROOM = new Room("Occupied room.");


    @Test
    public void schedule() throws SchedulingException {
        Course course = new Course("SWENG", CALENDAR);
        Room room = new Room("room1");
        RoomScheduler roomScheduler = new RoomSchedulerImpl(new RegistrarMock());
        roomScheduler.schedule(course, room);
        assertEquals(room, course.getRoom());
    }

    @Test
    public void coursesInRoom() {
        RoomScheduler roomScheduler = new RoomSchedulerImpl(new RegistrarMock());
        Set<Course> courses = roomScheduler.coursesInRoom(ROOM);
        assertEquals(1, courses.size());
        assertTrue(courses.contains(COURSE));
    }

    @Test(expected = SchedulingException.class)
    public void scheduleConflict() throws SchedulingException {
        Course course1 = new Course("SWENG", CALENDAR);
        Course course2 = new Course("CMPSC", CALENDAR);
        RoomScheduler roomScheduler = new RoomSchedulerImpl(new RegistrarMock());
        roomScheduler.schedule(course1, ROOM);

    }
}