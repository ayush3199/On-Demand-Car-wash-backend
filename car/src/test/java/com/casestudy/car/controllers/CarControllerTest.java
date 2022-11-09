package com.casestudy.car.controllers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.casestudy.car.models.Car;
import com.casestudy.car.services.CarServiceImpl;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@SpringBootTest(classes = { CarControllerTest.class })
 class CarControllerTest {
    @Mock
    private CarServiceImpl carServiceImpl;
    @InjectMocks
    private CarController carController;
    List<Car> myCar;
    Car car;
    @Test
    @Order(1)
    void testAddCar() {
        car = new Car(1, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        when(carServiceImpl.addCar(car)).thenReturn(car);
        ResponseEntity<Car> res = carController.addCar(car);
        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertEquals(car, res.getBody());
    }
    @Test
    @Order(2)
    void testDeleteCar() {
        car = new Car(101, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        int Id = 101;
        when(carServiceImpl.getCarById(Id)).thenReturn(car);
        assertEquals(car.getCarId(), Id);
        ResponseEntity<Car> res = carController.deleteCar(Id);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }
    @Test
    @Order(3)
    void testGetAllCars() {
        myCar = new ArrayList<>();
        myCar.add(new Car(101, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url"));
        when(carServiceImpl.getAllCars()).thenReturn(myCar);
        ResponseEntity<List<Car>> res = carController.getAllCars();
        assertEquals(HttpStatus.FOUND, res.getStatusCode());
    }
    @Test
    @Order(4)
    void testGetCarById() {
        car = new Car(101, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        int Id = 101;
        when(carServiceImpl.getCarById(Id)).thenReturn(car);
        ResponseEntity<Car> res = carController.getCarById(Id);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }
    @Test
    @Order(5)
    void testUpdateCar() {
        car = new Car(101, "Honda", "City", "2021", "Black", "UP25-AE-1001", "url");
        int Id = 101;
        when(carServiceImpl.getCarById(Id)).thenReturn(car);
        when(carServiceImpl.updateCar(car)).thenReturn(car);
        assertEquals(car.getCarId(), Id);
        ResponseEntity<Car> res = carController.updateCar(Id, car);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}
