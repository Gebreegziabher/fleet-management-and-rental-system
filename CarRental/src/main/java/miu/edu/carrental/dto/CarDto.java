package miu.edu.carrental.dto;

import miu.edu.carrental.common.Availability;

public class CarDto {
    private String licensePlate;
    private String type;
    private String brand;
    private double price;
    private Availability availability = Availability.AVAILABLE;

    public CarDto() {
    }

    public CarDto(String licensePlate, String type, String brand, double price, Availability availability) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.availability = availability;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "licensePlate='" + licensePlate + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                '}';
    }
}
