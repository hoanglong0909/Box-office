package com.cg.bo.controller;

import com.cg.bo.model.bussiness.*;
import com.cg.bo.model.bussiness.Class;
import com.cg.bo.model.projection.Schedule;
import com.cg.bo.model.projection.Seat;
import com.cg.bo.model.security.User;
import com.cg.bo.service.*;
import com.cg.bo.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/app")
public class AppController {

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

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ClassServiceImpl classService;

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private SeatServiceImpl seatService;

    @GetMapping
    public ModelAndView pageApp(){
        return new ModelAndView("/app/app");
    }

    @GetMapping("/allCategory")
    public ResponseEntity<Iterable<Category>> allCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/allProduct")
    @PreAuthorize("hasAnyAuthority('STAFF')")
    public ResponseEntity<Iterable<Product>> allProducts(){
        return new ResponseEntity<>(productService.findAllByOrderByProduct_idDesc(), HttpStatus.OK);
    }

    @GetMapping("/menuProductByCategory/{id}")
    public ResponseEntity<Iterable<Product>> productResponseEntity(@PathVariable Long id){
        Iterable<Product> products = productService.findAllByCategoryCategory_id(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product product = productService.findById(id).get();
        product.setAmount(1L);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/createNewMember")
    public ResponseEntity<Member> createNewMember(@RequestBody Member member){
        Class aClass = classService.findById(1L).get();
        member.setAClass(aClass);
        return new ResponseEntity<>(memberService.save(member), HttpStatus.CREATED);
    }

    @GetMapping("/searchMember/{string}")
    public ResponseEntity<Iterable<Member>> searchByMember(@PathVariable String string){
        return new ResponseEntity<>(memberService.searchByAllMember(string), HttpStatus.OK);
    }

    @GetMapping("/chooseByMember/{id}")
    public ResponseEntity<Member> chooseByMember(@PathVariable Long id){
        return new ResponseEntity<>(memberService.findById(id).get(),HttpStatus.OK);
    }

    @GetMapping("/allSchedules")
    public ResponseEntity<Iterable<Schedule>> allSchedule(){
        return new ResponseEntity<>(scheduleService.findAllByOrOrderBySchedule_dateAsc(),HttpStatus.OK);
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        String username = getPrincipal();
        User user = userService.findByName(username);
        order.setUser(user);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("/saveOrderDetail")
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail){
        return new ResponseEntity<>(orderDetailService.save(orderDetail),HttpStatus.CREATED);
    }

    @PostMapping("/saveTicket")
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket){
        return new ResponseEntity<>(ticketService.save(ticket),HttpStatus.CREATED);
    }

    @PutMapping("/setTakenSeat/{seatId}")
    public ResponseEntity<Seat> setTakenSeat(@PathVariable Long seatId){
        Seat seat = seatService.findById(seatId).get();
        if (seat.getSeatStatus().getId() == 2) {
            seat.getSeatStatus().setId(3L);
        }
        return new ResponseEntity<>(seatService.save(seat), HttpStatus.OK);
    }
}
