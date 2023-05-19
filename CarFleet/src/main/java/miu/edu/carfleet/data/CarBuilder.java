package miu.edu.carfleet.data;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.dto.CarDto;

public class CarBuilder {
    private String licensePlate;
    private String type;
    private String brand;
    private double price;
    private Availability availability;
    public CarBuilder licensePlate(String licensePlate){
        this.licensePlate = licensePlate;
        return this;
    }
    public CarBuilder type(String type){
        this.type = type;
        return this;
    }
    public CarBuilder brand(String brand){
        this.brand = brand;
        return this;
    }
    public CarBuilder price(double price){
        this.price = price;
        return this;
    }
    public CarBuilder availability(Availability availability){
        this.availability = availability;
        return this;
    }
    public CarDto build(){
        return new CarDto(licensePlate, type, brand, price, availability);
    }
}
