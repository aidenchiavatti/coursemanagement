package main.lib;

import java.util.HashSet;
import java.util.Set;

public class Room {
    private String roomNum;
    private Set<Course> courses;

    public Room(String roomNum) {
        this.roomNum = roomNum;
        this.courses = new HashSet<>();
    }

    public Set<Course> courses() {
        return this.courses;
    }

    protected void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return roomNum;
    }
}
