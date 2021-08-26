package com.cg.bo.service;

import com.cg.bo.model.bussiness.Order;
import com.cg.bo.model.dto.OrderAdmitDTO;
import com.cg.bo.model.dto.OrderDTO;
import com.cg.bo.model.dto.OrderMonthDTO;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface OrderService extends GeneralService<Order>{

    Iterable<Order> findAllByOrderByOrder_dateDesc();

    Iterable<Order> findOrderByOrderOrder_date(@Param("id") Long id);

    Iterable<Order> findAllByOrderDate(Date date);

    Iterable<Order> findOrderByTicket(Date date);

    Iterable<Order> findByUserId(Long id);

    Iterable<OrderDTO> findByMonthlyRevenue();

    Iterable<OrderAdmitDTO> findSumAdmitMonth(int month);

    Iterable<OrderMonthDTO> findTotalMonth(int month);
}
