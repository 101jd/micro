package org.example.controller;

import org.example.model.Cash;
import org.example.model.Order;
import org.example.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acc")
public class AccController {

    private final Service service;

    public AccController(Service service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello from acc");
    }

    @GetMapping("/pay")
    public ResponseEntity<Boolean> pay(){
        Boolean pay = service.pay(Cash.cost);
        Cash.cost = 0.0;
        return ResponseEntity.ok(pay);
    }

    @PostMapping("/plus")
    public ResponseEntity<Double> plusCost(@RequestBody Order order){
        return ResponseEntity.ok(service.plusCost(order.getPrice()));
    }

    @PostMapping("/minus")
    public ResponseEntity<Double> minusCost(@RequestBody Order order){
        return ResponseEntity.ok(service.minusCost(order.getPrice()));
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> balance(){
        return ResponseEntity.ok(Cash.amount);
    }

    @GetMapping("/price")
    public ResponseEntity<Double> price(){
        return ResponseEntity.ok(Cash.cost);
    }
}
