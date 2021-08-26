package com.cg.bo.controller;

import com.cg.bo.model.security.Role;
import com.cg.bo.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping("/allRole")
    public ResponseEntity<Iterable<Role>> getAllRole(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

}