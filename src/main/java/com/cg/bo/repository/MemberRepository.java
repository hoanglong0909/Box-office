package com.cg.bo.repository;

import com.cg.bo.model.bussiness.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(nativeQuery = true,value = "select * from members m where m.member_name like %:string% or m.phone_number like %:string% or m.email like %:string%")
    Iterable<Member> searchByAllMember(@Param("string")String string);
}
