package com.casestudy.washer.controllers;

import com.casestudy.washer.Repository.WasherRepo;
import com.casestudy.washer.models.Washer;
import com.casestudy.washer.serivces.WasherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
@RestController
@CrossOrigin
@RequestMapping("/washer")
public class WasherController {
    Logger logger= (Logger) LoggerFactory.getLogger(WasherController.class);
    @Autowired
    private WasherServiceImpl washerServiceImpl;
    @Autowired
    private WasherRepo washerRepo;
    @PostMapping("/washerlogin")
    public ResponseEntity<Washer> getLogin(@RequestBody Washer washer) {

        String email = washer.getEmail();
        String password = washer.getPassword();
        logger.debug("Request {}",washer);
        try {
            Washer obj = null;
            if (email != null && password != null) {
                obj = washerRepo.findByEmailAndPassword(email, password);
            }
            logger.debug("Response {}",obj);
            return new ResponseEntity<Washer>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // add washer

    @PostMapping("/add")
    public ResponseEntity<Washer> addWasher(@RequestBody Washer washer) {
        Washer w = null;
        logger.debug("Request {}",washer);
        try {
            w = this.washerServiceImpl.addWasher(washer);
            logger.debug("Response {}",HttpStatus.CREATED);
            return new ResponseEntity<Washer>(washer, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Response {}",HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get all Washer

    @GetMapping("/getAll")
    public ResponseEntity<List<Washer>> getAllWasher() {
        try {
            List<Washer> washerList = washerServiceImpl.getAllWashers();
            logger.debug("Response {}",HttpStatus.FOUND);
            return new ResponseEntity<List<Washer>>(washerList, HttpStatus.FOUND);
        } catch (Exception e) {
            logger.debug("Response {}",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get Washer by washerId

    @GetMapping("/get/{washerId}")

    public ResponseEntity<Washer> getWasherById(@PathVariable("washerId") int washerId) {
        Washer washer = washerServiceImpl.getWasherById(washerId);
        if (washer == null) {
            logger.debug("Response {}",HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.debug("Response {}",washer);
        return ResponseEntity.of(Optional.of(washer));

    }

    // // Get Washer by washerId
    // @GetMapping("/get/{washerId}")
    // public ResponseEntity<Washer> getWasherById(@PathVariable("washerId") int
    // washerId) {
    // Washer washer = washerServiceImpl.getWasherById(washerId);
    // // if (washer == null) {
    // // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // // }
    // // return ResponseEntity.of(Optional.of(washer));
    // Car cars = this.restTemplate.getForObject("http://localhost:5002/car/get/" +
    // washer.getWasherId(), Car.class);
    // washer.setCar(cars);
    // return ResponseEntity.of(Optional.of(washer));

    // }

    // delete Washer by washerId

    @DeleteMapping("/delete/{washerId}")

    public ResponseEntity<Washer> deleteWasher(@PathVariable("washerId") int washerId) {
        Washer washer = null;
        try {
            washer = washerServiceImpl.getWasherById(washerId);
            washerServiceImpl.deleteWasher(washer);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            logger.debug("Response {}",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Response {}",HttpStatus.OK);
        return new ResponseEntity<Washer>(washer, HttpStatus.OK);
    }

    // Update Washer by washerId
    @PutMapping("/update/{washerId}")
    public ResponseEntity<Washer> updateWasher(@PathVariable(value = "washerId") int washerId,
            @RequestBody Washer washer) {
        logger.debug("Response {}",washer);
        try {
            Washer exWasher = washerServiceImpl.getWasherById(washerId);
            exWasher.setFirstName(washer.getFirstName());
            exWasher.setLastName(washer.getLastName());
            exWasher.setEmail(washer.getEmail());
            exWasher.setPhone(washer.getPhone());
            exWasher.setAddress(washer.getAddress());
            exWasher.setPassword(washer.getPassword());

            Washer updatedWasher = washerServiceImpl.updateWasher(exWasher);
            return new ResponseEntity<Washer>(updatedWasher, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("Response {}",HttpStatus.CONFLICT);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
