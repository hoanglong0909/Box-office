package com.cg.bo.repository;

import com.cg.bo.model.bussiness.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("select t from Ticket t where t.order.order_id = :id")
    Iterable<Ticket> findAllByTicket(@Param("id") Long id);
}
