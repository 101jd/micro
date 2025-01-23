package org.example;

import org.example.model.Good;
import org.example.repo.Repo;
import org.example.service.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private Repo repo;

    @InjectMocks
    private Service service;

    @Test
    public void addGood(){
        Good good = new Good();
        good.setName("test");
        good.setId(5);
        good.setPrice(999.0);

        given(repo.addGood(good)).willReturn(good);


        service.addGood(good);
        repo.getGoodById(good.getId());

        verify(repo).getGoodById(good.getId());

    }
}
