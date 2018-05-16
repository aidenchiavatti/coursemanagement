package lib;


import main.lib.Course;
import main.lib.Room;
import main.lib.Student;

import java.util.Calendar;

public class CourseMock extends Course {
    public CourseMock(String name, Calendar calendar) {
        super(name, calendar);
    }

    @Override
    public void enroll(Student student) {
        super.enroll(student);
    }

    @Override
    public void setRoom(Room room) {
        super.setRoom(room);
    }

}
