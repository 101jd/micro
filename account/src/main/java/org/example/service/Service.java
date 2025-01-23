package org.example.service;

import org.example.exceptions.NotEnoughMoneyException;
import org.example.model.Cash;
import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
public class Service {

    public Boolean tryPay(double price) throws NotEnoughMoneyException{
        if (Cash.amount - price >= 0){
            return true;
        }
        throw new NotEnoughMoneyException();
    }

    public Boolean pay(double price){
        try {
            if (tryPay(price)) {
                Cash.amount -= price;
            }
        }catch (NotEnoughMoneyException e){
            return false;
        }
        return true;
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
