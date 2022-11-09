package com.casestudy.car.services;

import java.util.List;

import com.casestudy.car.models.Car;
import com.casestudy.car.repository.CarRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarServices {
    @Autowired
    private CarRepo carRepo;

    // Mocking
    public CarServiceImpl(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    // Add Car

    @Override
    public Car addCar(Car car) {
        carRepo.save(car);
        return car;
    }

    // Get all Cars

    @Override
    public List<Car> getAllCars() {
        List<Car> list = (List<Car>) this.carRepo.findAll();
        return list;
    }

    // Get Car By Id

    @Override
    public Car getCarById(int carId) {
        Car car = null;
        try {
            car = this.carRepo.findCarByCarId(carId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    // Delete Car by id
    @Override
    public void deleteCar(Car car) {
        this.carRepo.delete(car);

    }

    // Update Car by Id
    @Override
    public Car updateCar(Car car) {
        carRepo.save(car);
        return car;
    }

}
