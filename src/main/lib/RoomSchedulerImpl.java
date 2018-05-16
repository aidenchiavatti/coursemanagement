package main.lib;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RoomSchedulerImpl implements RoomScheduler {
    private Set<Room> rooms;

    private Registrar registrar;

    public RoomSchedulerImpl(Registrar registrar) {
        this.registrar = registrar;
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
        Set<Course> courses = coursesInRoom(room);
        boolean isRoomOccupied = courses.stream().anyMatch(c -> c.getTime().equals(course.getTime()));
        if(isRoomOccupied) {
            throw new SchedulingException("Room has course at same time.");
        } else {
            course.setRoom(room);
        }
    }

    @Override
    public Set<Course> coursesInRoom(Room room) {
        return registrar.allCourses().stream().filter(c -> Objects.nonNull(c.getRoom()))
                .filter(c -> c.getRoom().equals(room)).collect(Collectors.toSet());
    }
}
