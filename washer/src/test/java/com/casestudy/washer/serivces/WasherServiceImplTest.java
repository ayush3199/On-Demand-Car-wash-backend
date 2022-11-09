package com.casestudy.washer.serivces;

import com.casestudy.washer.Repository.WasherRepo;
import com.casestudy.washer.models.Washer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {WasherServiceImplTest.class})
public class WasherServiceImplTest {
    @Mock
    private WasherRepo washerRepo;

    @InjectMocks
    private WasherServiceImpl washerServiceImpl;

    public List<Washer> mywasher;

    @Test
    @Order(1)
    void addWasher() {
        mywasher=new ArrayList<Washer>();
        mywasher.add(new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321"));
        when(washerRepo.findAll()).thenReturn(mywasher);
        assertEquals(1,washerServiceImpl.getAllWashers().size());
    }

    @Test
    @Order(2)
    void getAllWashers() {
        mywasher = new ArrayList<Washer>();
        mywasher.add(new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321"));
        when(washerRepo.findAll()).thenReturn(mywasher);
        assertEquals(1,washerServiceImpl.getAllWashers().size());
    }

    @Test
    @Order(3)
    void getWasherById() {
        Washer mywasher=new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321");
        int washerId=101;
        when(washerRepo.findWasherByWasherId(washerId)).thenReturn(mywasher);
        assertEquals(washerId,washerServiceImpl.getWasherById(washerId).getWasherId());
    }

    @Test
    @Order(4)
    void updateWasher() {
        Washer mywasher=new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321");
        int washerId=101;
        when(washerServiceImpl.getWasherById(washerId)).thenReturn(mywasher);
        when(washerServiceImpl.updateWasher(mywasher)).thenReturn(mywasher);

        assertEquals(mywasher,washerServiceImpl.addWasher(mywasher));
    }

    @Test
    @Order(5)
    void deleteWasher() {
        Washer washer=new Washer(101,"ayush","Singh","asing@gmail.com","Bareilly","8171979905","Abc@321");
        washerServiceImpl.deleteWasher(washer);
        verify(washerRepo,times(1)).delete(washer);
    }
}