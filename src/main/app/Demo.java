package main.app;

import main.lib.*;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import java.util.Calendar;
import java.util.Collection;

class Demo {
    public static void main(String[] args) {
        try {
            Calendar date = new Calendar.Builder().setTimeOfDay(10, 10, 10).build();
            Course course = new Course("SWENG", date);
            Student student = new Student("Aiden");
            Registrar registrar = new RegistrarImpl();
            Room room = new Room("room1");
            RoomScheduler roomScheduler = new RoomSchedulerImpl();

            //create new course
            registrar.registerCourse(course);
            //create new student
            registrar.registerStudent(student);
            //add student to course
            registrar.enroll(course, student);
            //create room
            roomScheduler.register(room);
            //schedule course in room
            roomScheduler.schedule(course, room);
            //show all courses
            registrar.allCourses();
            //show all students
            registrar.allStudents();
            //show all rooms
            roomScheduler.allRooms();
            //show students in course
            course.students();
            //show courses for student
            student.courses();
            //show courses in room
            room.courses();

            Collection<Student> students = course.students();
            System.out.println(course.toString());
            for (Student s : students) {
                System.out.println(s);
            }
        } catch(EnrollmentException e) {
            e.printStackTrace();
        } catch(SchedulingException e) {
            e.printStackTrace();
        }
    }
}