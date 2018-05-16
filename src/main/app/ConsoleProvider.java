package main.app;

import main.lib.entity.Course;
import main.lib.entity.Room;
import main.lib.entity.Student;
import main.lib.exception.EnrollmentException;
import main.lib.service.Registrar;
import main.lib.service.RoomScheduler;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleProvider {
    private Registrar registrar;
    private RoomScheduler roomScheduler;
    private Scanner scan;

    public ConsoleProvider(Registrar registrar, RoomScheduler roomScheduler, Scanner scanner) {
        this.registrar = registrar;
        this.roomScheduler = roomScheduler;
        this.scan = scanner;
    }

    public void createCourse() {
        System.out.print("Enter Course Name: ");
        String courseName = scan.nextLine();
        System.out.print("Enter Course hour: ");
        String courseHourString = scan.nextLine();
        int courseHour = Integer.parseInt(courseHourString);
        Calendar calendar = new Calendar.Builder().setTimeOfDay(courseHour, 0, 0).build();
        registrar.registerCourse(new Course(courseName, calendar));
    }

    public void viewAllCourses() {
        if(registrar.allCourses().isEmpty()) {
            System.out.println("There are no courses.");
        }
        registrar.allCourses().forEach(System.out::println);
    }

    public void createStudent() {
        System.out.print("Enter Course Name: ");
        String name = scan.nextLine();
        registrar.registerStudent(new Student(name));
    }

    public void createRoom() {
        System.out.println("Enter Room name: ");
        String roomName = scan.nextLine();
        roomScheduler.register(new Room(roomName));
    }

    public void viewAllStudents() {
        if(registrar.allStudents().isEmpty()) {
            System.out.println("There are no students.");
        }
        registrar.allStudents().forEach(System.out::println);
    }

    public void viewAllRooms() {
        if(roomScheduler.allRooms().isEmpty()) {
            System.out.println("There are no rooms.");
        }
        roomScheduler.allRooms().forEach(System.out::println);
    }

    public void enrollStudent() {
        Course course = selectCourse();
        Student student = selectStudent(course);
        if(student == null) {
            System.out.println("There are no students able to enroll in this course.");
            return;
        }
        try {
            registrar.enroll(course, student);
        } catch (EnrollmentException e) {
            System.out.println("Could not enroll student: " + e.getMessage());
        }
    }

    private Student selectStudent(Course course) {
        //only show students who are not already enrolled in course
        List<Student> students = registrar.allStudents().stream()
                .filter(s -> !course.students().contains(s)).distinct().collect(Collectors.toList());
        if(students.size() == 0) {
            return null;
        }

        for(int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s%n", i+1, students.get(i).toString());
        }
        System.out.print("Select a student: ");
        while(true) {
            try {
                String indexString = scan.nextLine();
                int index = Integer.parseInt(indexString);
                return students.get(index - 1);
            } catch (Exception e) {
                System.out.print("Not a valid selection. Please select again: ");
            }
        }
    }

    private Course selectCourse() {
        List<Course> courses = new ArrayList<>(registrar.allCourses());
        for(int i = 0; i < courses.size(); i++) {
            System.out.printf("%d. %s%n", i+1, courses.get(i).toString());
        }
        System.out.print("Select a course: ");
        String indexString = scan.nextLine();
        int index = Integer.parseInt(indexString);
        return courses.get(index-1);
    }
}
