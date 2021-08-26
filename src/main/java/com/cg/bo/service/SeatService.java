package com.cg.bo.service;

import com.cg.bo.model.projection.Seat;

public interface SeatService extends GeneralService<Seat>{

    void initSeat(Long roomId);
}
