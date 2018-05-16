package main.app;

import main.lib.*;

import java.util.Calendar;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //prepare services
        Registrar registrar = new RegistrarImpl();
        RoomScheduler roomScheduler = new RoomSchedulerImpl(registrar);
        Scanner scan = new Scanner(System.in);
        ConsoleProvider provider = new ConsoleProvider(registrar, roomScheduler, scan);

        while(true) {
            System.out.println("1. Create Course");
            System.out.println("2. Create Student");
            System.out.println("3. Create Room");
            System.out.println("----------------");
            System.out.println("4. View All Courses");
            System.out.println("5. View All Students");
            System.out.println("6. View All Rooms");
            System.out.println("7. View Students in a Course");
            System.out.println("8. View Courses for a Student");
            System.out.println("9. View Courses in a Room");
            System.out.println("----------------");
            System.out.println("10. Enroll Student in Course");
            System.out.println("11. Assign Room to Course");
            System.out.print("Select an option: ");
            String inputString = scan.nextLine();
            int input = Integer.parseInt(inputString);
            switch (input) {
                case 1:
                    provider.createCourse();
                    break;
                case 2:
                    provider.createStudent();
                    break;
                case 3:
                    provider.createRoom();
                    break;
                case 4:
                    provider.viewAllCourses();
                    break;
                case 5:
                    provider.viewAllStudents();
                    break;
                case 6:
                    provider.viewAllRooms();
                    break;
                case 10:
                    provider.enrollStudent();
                    break;
                case 11:
                    //provider.createCourse();
                    break;
            }
            System.out.println();
        }

    }
}
