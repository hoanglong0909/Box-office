package com.cg.bo.service.impl;

import com.cg.bo.model.projection.Seat;
import com.cg.bo.repository.SeatRepository;
import com.cg.bo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Iterable<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void remove(Long id) {
        seatRepository.deleteById(id);
    }

    @Override
    public void initSeat(Long roomId) {
        seatRepository.initSeat(roomId);
    }
}
