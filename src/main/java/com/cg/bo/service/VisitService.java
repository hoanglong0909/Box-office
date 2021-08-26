package com.cg.bo.service;

import com.cg.bo.model.bussiness.Member;
import com.cg.bo.model.bussiness.Visit;

public interface VisitService extends GeneralService<Visit>{
    Iterable<Visit> findVisitsByActiveTrueAndMember(Member member);
}