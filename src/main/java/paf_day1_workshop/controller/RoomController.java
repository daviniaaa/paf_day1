package paf_day1_workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paf_day1_workshop.model.Room;
import paf_day1_workshop.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    RoomService rmSvc;

    @GetMapping("/count")
    public ResponseEntity<Integer> getRoomCount() {
        Integer countResult = rmSvc.count();
        // return ResponseEntity.ok().body(countResult);
        return new ResponseEntity<Integer>(countResult, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        rooms = rmSvc.findAll();

        if (rooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rooms);
        }
    }

    // http://localhost:8080/api/rooms/{room-id}/{cust-id}
    // http://localhost:8080/api/rooms/1/1
    // if there are multiple, either write in same sequence or specify by name
    // e.g. @PathVariable("room-id")
    @GetMapping("/{room-id}")
    public ResponseEntity<Room> getRoomById(@PathVariable int id) {
        Room room = rmSvc.findById(id);

        if (room == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(room);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {
        Boolean created = rmSvc.save(room);
        if (created) {
            return ResponseEntity.ok().body(created);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{room-id}")
    public ResponseEntity<Integer> deleteRoom(@PathVariable int id) {
        Integer deleted = rmSvc.deleteById(id);

        if (deleted == 1) {
            return ResponseEntity.ok().body(deleted);
        } else {
            return new ResponseEntity<Integer>(deleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Integer> updateRoom(@RequestBody Room room) {
        Integer updated = rmSvc.update(room);

        if (updated == 1) {
            return ResponseEntity.ok().body(updated);
        } else {
            return new ResponseEntity<Integer>(updated, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
