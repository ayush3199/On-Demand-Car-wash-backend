package com.casestudy.car.services;

import java.util.List;

import com.casestudy.car.models.Car;

public interface CarServices {
   public Car addCar(Car car);

   public List<Car> getAllCars();

   public Car getCarById(int carId);

   public void deleteCar(Car car);

   public Car updateCar(Car car);

}
