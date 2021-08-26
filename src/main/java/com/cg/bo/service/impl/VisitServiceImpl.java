package com.cg.bo.service.impl;

import com.cg.bo.model.bussiness.Member;
import com.cg.bo.model.bussiness.Visit;
import com.cg.bo.repository.VisitRepository;
import com.cg.bo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Iterable<Visit> findAll() {
        return visitRepository.findAll();
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void remove(Long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public Iterable<Visit> findVisitsByActiveTrueAndMember(Member member) {
        return visitRepository.findVisitsByActiveTrueAndMember(member);
    }
}