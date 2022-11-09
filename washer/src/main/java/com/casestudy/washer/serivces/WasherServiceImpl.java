package com.casestudy.washer.serivces;

import java.util.List;

import com.casestudy.washer.Repository.WasherRepo;
import com.casestudy.washer.models.Washer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WasherServiceImpl implements WasherService {
    @Autowired
    private WasherRepo washerRepo;

    // Add Washer
    @Override
    public Washer addWasher(Washer washer) {
        return washerRepo.save(washer);
    }

    // Get All Washer
    @Override
    public List<Washer> getAllWashers() {
        List<Washer> washerList = (List<Washer>) this.washerRepo.findAll();
        return washerList;
    }

    // Get Washer By WasherID
    @Override
    public Washer getWasherById(int washerId) {
        Washer washer = null;
        try {
            washer = this.washerRepo.findWasherByWasherId(washerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return washer;
    }

    // Update Washer
    @Override
    public Washer updateWasher(Washer washer) {
        washerRepo.save(washer);
        return washer;
    }

    // update Washer
    @Override
    public void deleteWasher(Washer washer) {
        washerRepo.delete(washer);
    }

}
