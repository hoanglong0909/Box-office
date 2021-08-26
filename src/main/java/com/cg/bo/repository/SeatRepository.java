package com.cg.bo.repository;

import com.cg.bo.model.projection.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Procedure
    void initSeat(Long roomId);
}
