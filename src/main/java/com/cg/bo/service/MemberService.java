package com.cg.bo.service;

import com.cg.bo.model.bussiness.Member;

public interface MemberService extends GeneralService<Member> {
    Iterable<Member> searchByAllMember(String string);
}
