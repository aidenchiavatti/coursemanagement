package main.lib.service;

import main.lib.entity.Course;
import main.lib.entity.Room;
import main.lib.exception.SchedulingException;

import java.util.HashSet;
import java.util.Set;

public class RoomSchedulerImpl implements RoomScheduler {
    private Set<Room> rooms;

    public RoomSchedulerImpl() {
        this.rooms = new HashSet<>();
    }

    @Override
    public void register(Room room) {
        rooms.add(room);
    }

    @Override
    public Set<Room> allRooms() {
        return rooms;
    }


    @Override
    public void schedule(Course course, Room room) throws SchedulingException {
        Set<Course> courses = room.courses();
        boolean isRoomOccupied = courses.stream().anyMatch(c -> c.getTime().equals(course.getTime()));
        if(isRoomOccupied) {
            throw new SchedulingException("Room has course at same time.");
        } else {
            course.setRoom(room);
            room.addCourse(course);
        }
    }
}
