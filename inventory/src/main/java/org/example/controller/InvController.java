package org.example.controller;

import org.example.model.Good;
import org.example.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inv")
public class InvController {

    private final Service service;

    public InvController(Service service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello from Inv");
    }

    @PostMapping("/grab")
    public ResponseEntity<Boolean> grabGood(@RequestBody Good good){
        return ResponseEntity.ok(service.grabGood(good));
    }

    @PostMapping("/return")
    public ResponseEntity<Boolean> returnGood(@RequestBody Good good){
        return ResponseEntity.ok(service.returnGood(good));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Good>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
