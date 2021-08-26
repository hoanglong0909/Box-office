package com.cg.bo.controller;

import com.cg.bo.model.bussiness.Class;
import com.cg.bo.model.bussiness.Member;
import com.cg.bo.model.bussiness.Product;
import com.cg.bo.service.ClassService;
import com.cg.bo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private ClassService classService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView listMember(){
        return new ModelAndView("dashboard/member/list");
    }

    @GetMapping("/allClass")
    public ResponseEntity<Iterable<Class>> allClasses(){
        return new ResponseEntity<>(classService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getByClasses")
    public ResponseEntity<Class> getClassById(@PathVariable Long id){
        Class aClass = classService.findById(id).get();
        return new ResponseEntity<>(aClass, HttpStatus.OK);
    }

    @GetMapping("/allMember")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    public ResponseEntity<Iterable<Member>> allMembers(){
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createNewMember")
    public ResponseEntity<Member> createNewMember(@RequestBody Member member){
        Class aClass = classService.findById(1L).get();
        member.setAClass(aClass);
        return new ResponseEntity<>(memberService.save(member), HttpStatus.CREATED);
    }

    @GetMapping("/createNewMember")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView createMemberForm(){
        return new ModelAndView("dashboard/member/create");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        Optional<Member> memberOptional = memberService.findById(id);
        if (!memberOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        memberService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Member> editMember(@RequestBody Member member,@PathVariable Long id){
        member.setMember_id(id);
        member.getAClass().setClass_name(classService.findById(member.getAClass().getClass_id()).get().getClass_name());
        return new ResponseEntity<>(memberService.save(member),HttpStatus.OK);
    }

    @GetMapping("/edit-member/{id}")
    public ResponseEntity<Member> memberResponseEntity(@PathVariable Long id){
        Member member = memberService.findById(id).get();
        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member){
        return new ResponseEntity<>(memberService.save(member), HttpStatus.OK);
    }

    @PutMapping("/update/{memberId}/{classId}")
    public ResponseEntity<Member> updateClassMember(@PathVariable Long memberId, @PathVariable Long classId){
        Member member = memberService.findById(memberId).get();
        Class aClass = classService.findById(classId).get();
        member.setAClass(aClass);
        return new ResponseEntity<>(memberService.save(member), HttpStatus.OK);
    }
}
