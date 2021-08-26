package com.cg.bo.service;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Schedule;
import com.cg.bo.model.projection.Show;

import java.util.Optional;

public interface ShowService extends GeneralService<Show>{
    Iterable<Show> findShowsBySchedule(Schedule schedule);

    Iterable<Show> findAllByScheduleAndStatusTrue(Schedule schedule);

    Iterable<Show> findAllByOrderByTime_startAsc();

    Iterable<Show> searchShowOfScheduleWhereShowNameLike(Long schedule_id, String film_name);

    Iterable<Show> findShowsByRoomName(String room_name,Long id);

    Optional<Show> findShowByRoom(Room room);
}