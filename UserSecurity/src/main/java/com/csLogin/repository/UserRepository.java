package com.csLogin.repository;

import com.csLogin.model.UserModel;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

	public UserModel findByEmail(String username);

}
