package org.example.service;

import org.example.model.Good;
import org.example.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final Repo repo;
    private final HttpHeaders headers;
    private final RestTemplate template;

    private final String INVENTORY = "http://127.0.0.1:8080/inv";
    private final String GRAB = "/grab";
    private final String RETURN = "/return";

    public Service(Repo repo, HttpHeaders headers, RestTemplate template) {
        this.repo = repo;
        this.headers = headers;
        this.template = template;
    }

    public Boolean addGood(Good good){
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Good> entity = new HttpEntity<>(repo.addGood(good), headers);

        ResponseEntity<Boolean> response =
                template.exchange(INVENTORY + GRAB, HttpMethod.POST, entity, Boolean.class);
        return response.getBody();
    }

    public Boolean removeGood(Good good){
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Good> entity = new HttpEntity<>(repo.removeGood(good), headers);

        ResponseEntity<Boolean> response =
                template.exchange(INVENTORY + RETURN, HttpMethod.POST, entity, Boolean.class);

        return response.getBody();
    }

    public List<Good> findAll(){
        return repo.findAll();
    }
}
