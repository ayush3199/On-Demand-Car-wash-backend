package com.casestudy.car.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.casestudy.car.models.Car;
import com.casestudy.car.services.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("car")
public class CarController {
    // creating object using autowired
    @Autowired
    private CarServiceImpl carServiceiImpl;

    // adding new car
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car c = null;
        try {
            c = this.carServiceiImpl.addCar(car);
            return new ResponseEntity<Car>(car, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // getting car by carid

    @GetMapping("/get/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable("carId") int carId) {
        Car car = carServiceiImpl.getCarById(carId);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(car));
    }

    // getting list of all cars

    @GetMapping("/getAll")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> carList = carServiceiImpl.getAllCars();
            return new ResponseEntity<List<Car>>(carList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // deleting car by carid
    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<Car> deleteCar(@PathVariable("carId") int carId) {
        Car car = null;
        try {
            car = carServiceiImpl.getCarById(carId);
            carServiceiImpl.deleteCar(car);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    // updating car bu car id
    @PutMapping("/update/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "carId") int carId, @RequestBody Car car) {
        try {
            Car exCar = carServiceiImpl.getCarById(carId);
            exCar.setCarName(car.getCarName());
            exCar.setCarNumber(car.getCarNumber());
            exCar.setColour(car.getColour());
            exCar.setCompany(car.getCompany());
            exCar.setImage(car.getImage());
            Car updated_Car = carServiceiImpl.updateCar(exCar);
            return new ResponseEntity<Car>(updated_Car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
