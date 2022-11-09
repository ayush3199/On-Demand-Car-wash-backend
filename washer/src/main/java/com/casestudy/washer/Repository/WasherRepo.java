package com.casestudy.washer.Repository;

import com.casestudy.washer.models.Washer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasherRepo extends MongoRepository<Washer, Integer> {

    public Washer findWasherByWasherId(int washerId);

    public Washer findByEmailAndPassword(String email, String password);

}
