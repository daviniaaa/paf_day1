package paf_day1_workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf_day1_workshop.model.Room;
import paf_day1_workshop.repository.RoomRepository;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepo;

    public int count() {
        return roomRepo.count();
    }

    public Boolean save(Room room) {
        return roomRepo.save(room);
    }

    public List<Room> findAll() {
        return roomRepo.getAllRooms();
    }

    public Room findById(int id) {
        return roomRepo.getRoomById(id);
    }

    public int update(Room room) {
        return roomRepo.update(room);
    }

    public int deleteById(int id) {
        return roomRepo.deleteById(id);
    }
}
