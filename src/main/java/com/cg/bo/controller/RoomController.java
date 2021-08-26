package com.cg.bo.controller;

import com.cg.bo.model.projection.Room;
import com.cg.bo.service.impl.RoomServiceImpl;
import com.cg.bo.service.impl.SeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private SeatServiceImpl seatService;


    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        return new ResponseEntity<>(roomService.save(room), HttpStatus.CREATED);
    }

    @PostMapping("/initSeat/{roomId}")
    public ResponseEntity<Room> initSeatForRoom(@PathVariable Long roomId){
        seatService.initSeat(roomId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Room> updateShowForRoom(@RequestBody Room room){
        return new ResponseEntity<>(roomService.save(room),HttpStatus.OK);
    }

}
