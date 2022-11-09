package com.casestudy.customer.models;

public class Car {
    private int carId;
    private String carNumber;
    private String company;
    private String carName;
    private String model;
    private String colour;

    public Car() {
    }

    public Car(int carId, String company, String carName, String model, String colour, String carNumber) {
        this.carId = carId;
        this.company = company;
        this.carName = carName;
        this.model = model;
        this.colour = colour;
        this.carNumber = carNumber;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

}
