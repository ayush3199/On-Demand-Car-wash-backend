package com.casestudy.washer.controllers;

import com.casestudy.washer.models.Washer;
import com.casestudy.washer.serivces.WasherServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {WasherControllerTest.class})
 public class WasherControllerTest {
    @Mock
    private WasherServiceImpl washerServiceImpl;
    @InjectMocks
    private WasherController washerController;
    List<Washer> mywasher;
    Washer washer;


    @Test
    @Order(1)
    void addWasher() {
        washer=new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321");
        when(washerServiceImpl.addWasher(washer)).thenReturn(washer);
        ResponseEntity<Washer> res = washerController.addWasher(washer);
        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertEquals(washer, res.getBody());
    }

    @Test
    @Order(2)
    void getAllWasher() {
        mywasher= new ArrayList<>();
        mywasher.add(new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321"));
        mywasher.add(new Washer(102,"shanky","Pandey","shanky@gmail.com","Bareilly","968574123","Abc@321"));
        when(washerServiceImpl.getAllWashers()).thenReturn(mywasher);
        ResponseEntity<List<Washer>> res = washerController.getAllWasher();
        assertEquals(HttpStatus.FOUND, res.getStatusCode());

    }

    @Test
    @Order(3)
    void getWasherById() {
        washer=new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321");
        int washerId=101;
        when(washerServiceImpl.getWasherById(washerId)).thenReturn(washer);
        ResponseEntity<Washer> res = washerController.getWasherById(washerId);
        assertEquals(HttpStatus.OK, res.getStatusCode());


    }

    @Test
    @Order(4)
    void deleteWasher() {
        washer=new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321");
        int washerId=101;
        when(washerServiceImpl.getWasherById(washerId)).thenReturn(washer);
        assertEquals(washer.getWasherId(),washerId);
        ResponseEntity<Washer> res = washerController.deleteWasher(washerId);
        assertEquals(HttpStatus.OK, res.getStatusCode());

    }

    @Test
    @Order(5)
    void updateWasher() {
        washer=new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321");
        int washerId=101;
        when(washerServiceImpl.getWasherById(washerId)).thenReturn(washer);
        when(washerServiceImpl.updateWasher(washer)).thenReturn(washer);
        assertEquals(washer.getWasherId(),washerId);
        ResponseEntity<Washer> res = washerController.updateWasher(washerId,washer);
        assertEquals(HttpStatus.OK, res.getStatusCode());

    }
}