package client.dto;

public class CarAvailabilityDto {
    private String id;
    private String type;
    private String brand;
    private int quantity;
    public CarAvailabilityDto() {
    }
    public CarAvailabilityDto(String type, String brand) {
        this.type = type;
        this.brand = brand;
    }
    public CarAvailabilityDto(String type, String brand, int quantity) {
        this.type = type;
        this.brand = brand;
        this.quantity = quantity;
    }
    public CarAvailabilityDto(String id, String type, String brand, int quantity) {
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
