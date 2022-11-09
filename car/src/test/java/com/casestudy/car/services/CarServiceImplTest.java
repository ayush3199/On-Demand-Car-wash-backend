package com.casestudy.car.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.casestudy.car.models.Car;
import com.casestudy.car.repository.CarRepo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(classes = { CarServiceImplTest.class })
 class CarServiceImplTest {
    @Mock
    CarRepo carRepo;
    @InjectMocks
    CarServiceImpl carServiceImpl;
    @Test
    @Order(1)
    void testGetAllCar() {
        List<Car> myCars = new ArrayList<>();
        myCars.add(new Car(1, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url"));
        myCars.add(new Car(2, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url"));
        when(carRepo.findAll()).thenReturn(myCars);
        assertEquals(2, carServiceImpl.getAllCars().size());
    }
    @Test
    @Order(2)
    void testGetCarById() {
        Car car = new Car(1, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        int Id = 1;
        when(carRepo.findCarByCarId(Id)).thenReturn(car);
        assertEquals(Id, carServiceImpl.getCarById(Id).getCarId());
    }
    @Test
    @Order(3)
    void testAddCar() {
        Car car = new Car(1, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        when(carRepo.save(car)).thenReturn(car);
        assertEquals(car, carServiceImpl.addCar(car));
    }
    @Test
    @Order(4)
    void testUpdateCar() {
        Car car = new Car(1, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        when(carRepo.save(car)).thenReturn(car);
        assertEquals(car, carServiceImpl.updateCar(car));
    }
    @Test
    @Order(5)
    void testRemoveCar() {
        Car car = new Car(1, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        carServiceImpl.deleteCar(car);
        verify(carRepo, times(1)).delete(car);
    }
}
