package main.lib;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
