package com.cg.bo.repository;

import com.cg.bo.model.bussiness.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    @Query("select o from OrderDetail o where o.order.order_id = :id")
    Iterable<OrderDetail> findAllByOrderDetail(@Param("id") Long id);
}
