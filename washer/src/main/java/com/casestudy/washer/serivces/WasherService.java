package com.casestudy.washer.serivces;

import java.util.List;

import com.casestudy.washer.models.Washer;

public interface WasherService {
    public Washer addWasher(Washer washer);

    public List<Washer> getAllWashers();

    public Washer getWasherById(int washerId);

    public Washer updateWasher(Washer washer);

    public void deleteWasher(Washer washer);
}
