package main.lib;

import java.util.Set;

public interface RoomScheduler {
    void register(Room room);

    Set<Room> allRooms();

    void schedule(Course course, Room room) throws SchedulingException ;

    Set<Course> coursesInRoom(Room room);
}
