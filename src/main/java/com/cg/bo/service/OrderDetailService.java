package com.cg.bo.service;

import com.cg.bo.model.bussiness.OrderDetail;
import org.springframework.data.repository.query.Param;

public interface OrderDetailService extends GeneralService<OrderDetail>{
    Iterable<OrderDetail> findAllByOrderDetail(Long id);
}
