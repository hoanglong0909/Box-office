package com.cg.bo.controller;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Seat;
import com.cg.bo.service.RoomService;
import com.cg.bo.service.SeatService;
import com.cg.bo.service.impl.RoomServiceImpl;
import com.cg.bo.service.impl.SeatServiceImpl;
import com.cg.bo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private SeatServiceImpl seatService;

    @Autowired
    UserServiceImpl userService;

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/getSeatsByRoom/{roomId}")
    public ResponseEntity<Iterable<Seat>> listSeat(@PathVariable Long roomId){
        Room room = roomService.findById(roomId).get();
        return new ResponseEntity<>(roomService.findSeatListRoom(room), HttpStatus.OK);
    }

    @PutMapping("/selectSeatById/{id}")
    public ResponseEntity<Seat> selectSeatById(@PathVariable Long id){
        Seat seat = seatService.findById(id).get();
        if (seat.getSeatStatus().getId() == 1L){
            seat.getSeatStatus().setId(2L);
        }
        else if (seat.getSeatStatus().getId() == 2L){
            seat.getSeatStatus().setId(1L);
        }
        seat.setUser(userService.findByName(getPrincipal()));
        return new ResponseEntity<>(seatService.save(seat), HttpStatus.OK);
    }

    @PutMapping("/setEmpty/{seatId}")
    public ResponseEntity<Seat> setEmptySeat(@PathVariable Long seatId){
        Seat seat = seatService.findById(seatId).get();
        seat.getSeatStatus().setId(1L);
        seat.setUser(userService.findUserById(1L).get());
        return new ResponseEntity<>(seatService.save(seat), HttpStatus.OK);
    }

}