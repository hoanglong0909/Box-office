package com.cg.bo.service.impl;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Seat;
import com.cg.bo.repository.RoomRepository;
import com.cg.bo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Iterable<Seat> findSeatListRoom(Room room) {
        return roomRepository.findSeatListRoom(room);
    }
}
