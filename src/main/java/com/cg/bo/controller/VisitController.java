package com.cg.bo.controller;

import com.cg.bo.model.bussiness.Member;
import com.cg.bo.model.bussiness.Visit;
import com.cg.bo.service.impl.MemberServiceImpl;
import com.cg.bo.service.impl.VisitServiceImpl;
import com.cg.bo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitServiceImpl visitService;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private MemberServiceImpl memberService;

    @PostMapping("/save")
    public ResponseEntity<Visit> saveNewVisit(@RequestBody Visit visit){
        visit.setActive(true);
        return new ResponseEntity<>(visitService.save(visit), HttpStatus.OK);
    }

    @GetMapping("/findByMemberId/{memberId}")
    public ResponseEntity<Iterable<Visit>> findVisitsByMemberId(@PathVariable Long memberId){
        Member member = memberService.findById(memberId).get();
        Iterable<Visit> visits = visitService.findVisitsByActiveTrueAndMember(member);
        updateListVisit(visits);
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    public void updateListVisit(Iterable<Visit> visits){
        for (Visit v :
                visits) {
            if (dateUtils.getCurrentDate().compareTo(v.getExp_date()) > 0){
                v.setActive(false);
                visitService.save(v);
            }
        }
    }
}