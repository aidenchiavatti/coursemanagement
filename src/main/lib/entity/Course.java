package main.lib.entity;

import java.util.Calendar;
import java.util.HashSet;

public class Course {
    private String name;
    private Calendar time;
    private HashSet<Student> students;
    private Room room;

    public Course(String name, Calendar time) {
        this.name = name;
        this.time = time;
        this.students = new HashSet<>();
    }

    public HashSet<Student> students() {
        return students;
    }

    public void enroll(Student student) {
        students.add(student);
    }

    @Override public String toString() {
        return String.format("Course name: %s%nCourse Time: %s", this.name, this.time.getTime().toString());
    }

    public Calendar getTime() {
        return time;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}


