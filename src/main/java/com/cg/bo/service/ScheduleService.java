package com.cg.bo.service;

import com.cg.bo.model.projection.Schedule;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.Month;

public interface ScheduleService extends GeneralService<Schedule>{
    Iterable<Schedule> findAllByOrOrderBySchedule_dateAsc();

    Iterable<Schedule> searchBySchedule_date(String schedule_date);

    Iterable<Schedule> findScheduleByMonth(@Param("month") String month);
}
