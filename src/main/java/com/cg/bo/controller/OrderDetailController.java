package com.cg.bo.controller;


import com.cg.bo.model.bussiness.OrderDetail;
import com.cg.bo.service.impl.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    @GetMapping("/getAllOrderDetail/{orderDetail_id}")
    public ResponseEntity<Iterable<OrderDetail>> findAllOrderDetailById(@PathVariable Long orderDetail_id ){
        Iterable<OrderDetail> orderDetails = orderDetailService.findAllByOrderDetail(orderDetail_id);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}