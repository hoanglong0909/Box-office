package com.cg.bo.repository;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Transactional
    @Modifying
    @Query("select s from Seat s where s.room= :room")
    Iterable<Seat> findSeatListRoom(@Param("room") Room room);
}
