package org.example.exceptions;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(){
        super("Not enough money");
    }
}
