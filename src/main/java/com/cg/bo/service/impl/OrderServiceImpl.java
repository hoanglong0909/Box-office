package com.cg.bo.service.impl;

import com.cg.bo.model.bussiness.Order;
import com.cg.bo.model.dto.OrderAdmitDTO;
import com.cg.bo.model.dto.OrderDTO;
import com.cg.bo.model.dto.OrderMonthDTO;
import com.cg.bo.repository.OrderRepository;
import com.cg.bo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> findAllByOrderByOrder_dateDesc() {
        return orderRepository.findAllByOrderByOrder_dateDesc();
    }

    @Override
    public Iterable<Order> findOrderByOrderOrder_date(Long id) {
        return orderRepository.findOrderByOrderOrder_date(id);
    }

    @Override
    public Iterable<Order> findAllByOrderDate(Date date) {
        return orderRepository.findAllByOrderDate(date);
    }

    @Override
    public Iterable<Order> findOrderByTicket(Date date) {
        return orderRepository.findOrderByTicket(date);
    }

    @Override
    public Iterable<Order> findByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public Iterable<OrderDTO> findByMonthlyRevenue() {
        return orderRepository.findByMonthlyRevenue();
    }

    @Override
    public Iterable<OrderAdmitDTO> findSumAdmitMonth(int month) {
        return orderRepository.findSumAdmitMonth(month);
    }

    @Override
    public Iterable<OrderMonthDTO> findTotalMonth(int month) {
        return orderRepository.findTotalMonth(month);
    }
}
