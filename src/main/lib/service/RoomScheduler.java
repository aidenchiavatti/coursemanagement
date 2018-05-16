package main.lib.service;

import main.lib.entity.Course;
import main.lib.entity.Room;
import main.lib.exception.SchedulingException;

import java.util.Set;

public interface RoomScheduler {
    void register(Room room);

    Set<Room> allRooms();

    void schedule(Course course, Room room) throws SchedulingException;
}
