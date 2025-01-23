package org.example;

import org.example.exceptions.NotEnoughMoneyException;
import org.example.model.Order;
import org.example.service.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Spy
    @InjectMocks
    Service service;

    @Test
    public void NotEnoughMoney(){
        Order order = new Order();
        order.setPrice(1001.1);

        assertThrows(NotEnoughMoneyException.class, () -> service.tryPay(order.getPrice()));

    }
}
