package main.lib;

import org.junit.Test;

import static main.Constants.CALENDAR;
import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void addCourse() {
        Room room = new Room("room1");
        Course course = new Course("SWENG", CALENDAR);
        room.addCourse(course);
        assertEquals(1, room.courses().size());
        assertTrue(room.courses().contains(course));
    }
}