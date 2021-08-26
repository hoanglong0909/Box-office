package com.cg.bo.service;

import com.cg.bo.model.bussiness.Ticket;
import org.springframework.data.repository.query.Param;

public interface TicketService extends GeneralService<Ticket>{
    Iterable<Ticket> findAllByTicket(Long id);
}
