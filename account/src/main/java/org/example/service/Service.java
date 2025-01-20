package org.example.service;

import org.example.model.Cash;
import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
public class Service {

    public Boolean pay(double price){
        if (Cash.amount - price >= 0){
            Cash.amount -= price;
            return true;
        }
        return false;
    }

    public Double plusCost (Double price) {
        Cash.cost += price;
        return Cash.cost;
    }

    public Double minusCost(Double price) {
        Cash.cost -= price;
        return Cash.cost;
    }
}
