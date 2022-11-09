package com.casestudy.washer.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "washer")
public class Washer {
    @Id
    private int washerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String password;

    // @Autowired
    // private Car car;

    // public Car getCar() {
    // return car;
    // }

    // public void setCar(Car car) {
    // this.car = car;
    // }

    public Washer() {
    }

    public Washer(int washerId, String firstName, String lastName, String email, String phone, String address,
            String password) {
        this.washerId = washerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public int getWasherId() {
        return washerId;
    }

    public void setWasherId(int washerId) {
        this.washerId = washerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
