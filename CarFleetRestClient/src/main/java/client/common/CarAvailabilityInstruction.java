package client.common;

public class CarAvailabilityInstruction {
    private String licencePlate;
    private Availability availability;
    private String brand;
    private String type;

    private CarAvailabilityInstruction(){}
    public CarAvailabilityInstruction(String licencePlate, Availability availability) {
        this.licencePlate = licencePlate;
        this.availability = availability;
    }
    public CarAvailabilityInstruction(String licencePlate, String brand, String type, Availability availability) {
        this.licencePlate = licencePlate;
        this.availability = availability;
        this.brand = brand;
        this.type = type;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Availability getAvailability() {
        return availability;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
