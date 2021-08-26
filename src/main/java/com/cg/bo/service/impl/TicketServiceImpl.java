package com.cg.bo.service.impl;

import com.cg.bo.model.bussiness.Ticket;
import com.cg.bo.repository.TicketRepository;
import com.cg.bo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Iterable<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void remove(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Iterable<Ticket> findAllByTicket(Long id) {
        return ticketRepository.findAllByTicket(id);
    }
}
