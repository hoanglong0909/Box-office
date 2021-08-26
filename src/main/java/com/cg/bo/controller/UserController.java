package com.cg.bo.controller;

import com.cg.bo.model.bussiness.Product;
import com.cg.bo.security.UserPrincipal;
import com.cg.bo.service.impl.RoleServiceImpl;
import com.cg.bo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        return new ModelAndView("/dashboard/user/create");
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserPrincipal> getUserByUsername(){
        return new ResponseEntity<>(userService.findByUsername(getPrincipal()), HttpStatus.OK);
    }

    @GetMapping("/user-deleted")
    public ModelAndView getDeletedUsersForm(){
        return new ModelAndView("/dashboard/user/deleted");
    }

    @PostMapping("/create")
    public ResponseEntity<User> createNewUser(@RequestBody com.cg.bo.model.security.User user){
        user.setRole(roleService.findById(user.getRole().getId()).get());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (userService.isContainEmail(user.getEmail()) && userService.isContainUsername(user.getUsername()) && userService.isContainPhone(user.getPhone())){
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    public ModelAndView listUser(){
        return new ModelAndView("/dashboard/user/list");
    }

    @GetMapping("/listUsers")
    public ResponseEntity<Iterable<com.cg.bo.model.security.User>> listUsers(){
        return new ResponseEntity<>(userService.findAllByDeletedFalse(),HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<com.cg.bo.model.security.User> deleteUserById(@PathVariable Long id){
        com.cg.bo.model.security.User user = userService.findUserById(id).get();
        user.setDeleted(!user.isDeleted());
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<com.cg.bo.model.security.User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id).get(),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<com.cg.bo.model.security.User> editUser(@PathVariable Long id, @RequestBody com.cg.bo.model.security.User user){
        String password = userService.findUserById(id).get().getPassword();
        user.setId(id);
        user.setPassword(password);
        user.setRole(roleService.findById(user.getRole().getId()).get());
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }

    @GetMapping("/getDeletedUser")
    public ResponseEntity<Iterable<com.cg.bo.model.security.User>> getDeletedUsers(){
        return new ResponseEntity<>(userService.findAllByDeletedTrue(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<com.cg.bo.model.security.User> userOptional = userService.findUserById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}