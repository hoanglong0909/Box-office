package com.cg.bo.service;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Seat;
import org.springframework.data.repository.query.Param;

public interface RoomService extends GeneralService<Room>{
    Iterable<Seat> findSeatListRoom(@Param("room") Room room);
}
