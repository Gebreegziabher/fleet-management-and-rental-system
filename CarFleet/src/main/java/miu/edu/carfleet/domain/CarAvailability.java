package miu.edu.carfleet.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CarAvailability {
    @Id
    @Indexed(unique = true)
    private String id;
    private String type;
    private String brand;
    private int quantity;
    public CarAvailability() {
    }
    public CarAvailability(String type, String brand, int quantity) {
        this.type = type;
        this.brand = brand;
        this.quantity = quantity;
    }
    public CarAvailability(String id, String type, String brand, int quantity) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CarAvailability{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
