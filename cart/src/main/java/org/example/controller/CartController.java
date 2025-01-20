package org.example.controller;

import org.example.model.Good;
import org.example.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final Service service;

    public CartController(Service service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello from cart");
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addGood(@RequestBody Good good){
        return ResponseEntity.ok(service.addGood(good));
    }

    @PostMapping("/remove")
    public ResponseEntity<Boolean> removeGood(@RequestBody Good good){
        return ResponseEntity.ok(service.removeGood(good));
    }

    @GetMapping("/goods")
    public ResponseEntity<List<Good>> allGoods(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/buy")
    public ResponseEntity<String> buy(){
        return ResponseEntity.ok(service.buy());
    }
}
