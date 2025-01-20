package org.example.service;

import org.example.model.Good;
import org.example.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final Repo repo;

    public Service(Repo repo) {
        this.repo = repo;
    }

    public List<Good> findAll(){
        return repo.findAll();
    }

    public Boolean grabGood(Good good){
        return repo.grab(good);
    }

    public Boolean returnGood(Good good){
        return repo.returnGood(good);
    }
}
