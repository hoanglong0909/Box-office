package com.cg.bo.controller;

import com.cg.bo.model.bussiness.Ticket;
import com.cg.bo.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;


    @GetMapping("/allTicket")
    public ResponseEntity<Iterable<Ticket>> allTicket(){
        Iterable<Ticket> findAllTicket = ticketService.findAll();
        return new ResponseEntity<>(findAllTicket, HttpStatus.OK);
    }

    @GetMapping("/allTicketByOder/{ticket_id}")
    public ResponseEntity<Iterable<Ticket>> listTicketBy(@PathVariable Long ticket_id){
        Iterable<Ticket> findAllTicket = ticketService.findAllByTicket(ticket_id);
        return new ResponseEntity<>(findAllTicket, HttpStatus.OK);
    }
}
