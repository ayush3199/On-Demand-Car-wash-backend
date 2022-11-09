package com.casestudy.car.repository;

import com.casestudy.car.models.Car;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends MongoRepository<Car, Integer> {

    public Car findCarByCarId(int carId);

    // public Void getAllCars();

}
