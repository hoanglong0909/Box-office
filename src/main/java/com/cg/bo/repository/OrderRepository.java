package com.cg.bo.repository;

import com.cg.bo.model.bussiness.Order;
import com.cg.bo.model.dto.OrderAdmitDTO;
import com.cg.bo.model.dto.OrderDTO;
import com.cg.bo.model.dto.OrderMonthDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o order by o.order_id desc")
    Iterable<Order> findAllByOrderByOrder_dateDesc();

    @Query("select o from Order o where o.order_date= :id")
    Iterable<Order> findOrderByOrderOrder_date(@Param("id") Long id);

    @Query("select o from Order o where o.order_date = ?1")
    Iterable<Order> findAllByOrderDate(Date date);

    @Query("select o.total_product,o.total_ticket,o.total_price,t.ticket_id from Order o inner join Ticket t on o.order_id = t.order.order_id where o.order_date = ?1")
    Iterable<Order> findOrderByTicket(Date date);

    @Query("select o from Order o where o.user.id = ?1")
    Iterable<Order> findByUserId(Long id);

    @Query(nativeQuery = true, value = "SELECT month(o.order_date) as `month` ,\n" +
            " sum(o.total_product) as total_product,\n" +
            " sum(o.total_ticket) as total_ticket,\n" +
            " sum(o.total_price) as total_price \n" +
            "FROM orders o\n" +
            "WHERE year(o.order_date)\n" +
            "GROUP BY month(o.order_date)")
    Iterable<OrderDTO> findByMonthlyRevenue();


    @Query(nativeQuery = true, value = "SELECT DISTINCT month(o.order_date) as `month`,f.admit as admit from orders o \n" +
            "inner join tickets t on o.order_id = t.order_id\n" +
            "inner join shows s on t.show_id = s.show_id\n" +
            "inner join films f on s.film_id = f.film_id where month(o.order_date) = ?;")
    Iterable<OrderAdmitDTO> findSumAdmitMonth(int month);

    @Query(nativeQuery = true,value = "select month(o.order_date) as `month`, sum(o.total_price) as total_price from orders o\n" +
            "where month(o.order_date) = ?")
    Iterable<OrderMonthDTO> findTotalMonth(int month);
}
