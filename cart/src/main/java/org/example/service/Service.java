package org.example.service;

import org.example.model.Good;
import org.example.model.Order;
import org.example.repo.Repo;
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

    private final String ACCOUNT = "http://127.0.0.1:8080/acc";
    private final String PLUS_COST = "/plus";
    private final String MINUS_COST = "/minus";
    private final String PAY = "/pay";

    public Service(Repo repo, HttpHeaders headers, RestTemplate template) {
        this.repo = repo;
        this.headers = headers;
        this.template = template;
    }


    public Boolean addGood(Good good){
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Order order = new Order();
        order.setPrice(good.getPrice());

        HttpEntity<Good> entityGood = new HttpEntity<>(good, headers);
        HttpEntity<Order> entityOrder = new HttpEntity<>(order, headers);

        ResponseEntity<Boolean> responseInv =
                template.exchange(INVENTORY + GRAB, HttpMethod.POST, entityGood, Boolean.class);
        if (responseInv.getBody())
            repo.addGood(good);
        ResponseEntity<Double> responseAcc =
                template.exchange(ACCOUNT + PLUS_COST, HttpMethod.POST, entityOrder, Double.class);
        return responseInv.getBody();
    }


    public Boolean removeGood(Good good){
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Order order = new Order();
        order.setPrice(good.getPrice());

        HttpEntity<Good> entityGood = new HttpEntity<>(good, headers);
        HttpEntity<Order> entityOrder = new HttpEntity<>(order, headers);

        ResponseEntity<Boolean> responseInv =
                template.exchange(INVENTORY + RETURN, HttpMethod.POST, entityGood, Boolean.class);
        ResponseEntity<Double> responseAcc =
                template.exchange(ACCOUNT + MINUS_COST, HttpMethod.POST, entityOrder, Double.class);
        if (responseInv.getBody())
            repo.removeGood(good);

        return responseInv.getBody();
    }

    public List<Good> findAll(){
        return repo.findAll();
    }

    public String buy(){
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response =
                template.exchange(ACCOUNT + PAY, HttpMethod.GET, entity, Boolean.class);
        if (response.getBody() && repo.flush())
            return "Thank you for bying";
        return "Can't complete operation";
    }
}
