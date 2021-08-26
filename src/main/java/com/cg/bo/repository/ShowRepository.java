package com.cg.bo.repository;

import com.cg.bo.model.projection.Room;
import com.cg.bo.model.projection.Schedule;
import com.cg.bo.model.projection.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Transactional
    @Modifying
    @Query("select s from Show s where s.schedule = :schedule order by s.time_start asc")
    Iterable<Show> findShowsBySchedule(@Param("schedule") Schedule schedule);

    Iterable<Show> findAllByScheduleAndStatusTrue(Schedule schedule);

    @Query("select s from Show s order by s.time_start asc ")
    Iterable<Show> findAllByOrderByTime_startAsc();


    @Query(nativeQuery = true,value = "SELECT s.show_id, s.status, s.time_end, s.time_start, s.film_id, f.film_name, s.room_id," +
            " s.schedule_id FROM shows s INNER JOIN films f ON s.film_id = f.film_id WHERE s.schedule_id = ? AND film_name LIKE %?%")
    Iterable<Show> searchShowOfScheduleWhereShowNameLike(Long schedule_id, String film_name);

    @Query(nativeQuery = true,value = "select * from shows s inner join \n" +
            "rooms r on s.room_id = r.room_id where r.room_name = :room_name and s.schedule_id = :id")
    Iterable<Show> findShowsByRoomName(@Param("room_name")String room_name,@Param("id") Long id);

    Optional<Show> findShowByRoom(Room room);
}