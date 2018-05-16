package main.lib.service;

import main.lib.entity.Course;
import main.lib.entity.Room;
import main.lib.exception.SchedulingException;
import main.lib.service.RoomScheduler;
import main.lib.service.RoomSchedulerImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static main.Constants.CALENDAR;
import static org.junit.Assert.*;

public class RoomSchedulerTest {

    private final Course COURSE = new Course("SWENG", CALENDAR);
    private final Room ROOM = new RoomMock("Occupied room.");

    class RoomMock extends Room {

        public RoomMock(String roomNum) {
            super(roomNum);
        }

        @Override
        public void addCourse(Course course) {
            //
        }

        @Override
        public Set<Course> courses() {
            HashSet<Course> courses = new HashSet<>();
            courses.add(COURSE);
            return courses;
        }
    }

    @Test
    public void schedule() throws SchedulingException {
        Course course = new Course("SWENG", CALENDAR);
        Room room = new Room("room1");
        RoomScheduler roomScheduler = new RoomSchedulerImpl();
        roomScheduler.schedule(course, room);
        assertEquals(room, course.getRoom());
        assertTrue(room.courses().contains(course));
    }

    @Test
    public void coursesInRoom() {
        Set<Course> courses = ROOM.courses();
        assertEquals(1, courses.size());
        assertTrue(courses.contains(COURSE));
    }

    @Test(expected = SchedulingException.class)
    public void scheduleConflict() throws SchedulingException {
        Course course1 = new Course("SWENG", CALENDAR);
        RoomScheduler roomScheduler = new RoomSchedulerImpl();
        roomScheduler.schedule(course1, ROOM);
    }
}