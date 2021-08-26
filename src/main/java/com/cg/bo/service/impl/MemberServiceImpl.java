package com.cg.bo.service.impl;

import com.cg.bo.model.bussiness.Member;
import com.cg.bo.repository.MemberRepository;
import com.cg.bo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public Iterable<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void remove(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Iterable<Member> searchByAllMember(String string) {
        return memberRepository.searchByAllMember(string);
    }
}
