package com.cg.bo.repository;

import com.cg.bo.model.bussiness.Member;
import com.cg.bo.model.bussiness.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Transactional
    @Modifying
    @Query("select  v from Visit v where v.isActive = true and v.member = ?1 ")
    Iterable<Visit> findVisitsByActiveTrueAndMember(Member member);


}