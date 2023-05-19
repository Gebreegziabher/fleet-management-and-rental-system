package client.dto;

import java.util.Date;

public class RentalDto {
    private long id;
    private String rentalLocation;
    private String rentOutProcessedBy;//Name of rental employee
    private String returnProcessedBy;//Name of rental employee
    private Date rentedOutDate;
    private Date returnedDate;

    public RentalDto() {
    }

    public RentalDto(long id, String rentalLocation, String rentOutProcessedBy, String returnProcessedBy, Date rentedOutDate, Date returnedDate) {
        this.id = id;
        this.rentalLocation = rentalLocation;
        this.rentOutProcessedBy = rentOutProcessedBy;
        this.returnProcessedBy = returnProcessedBy;
        this.rentedOutDate = rentedOutDate;
        this.returnedDate = returnedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRentalLocation() {
        return rentalLocation;
    }

    public void setRentalLocation(String rentalLocation) {
        this.rentalLocation = rentalLocation;
    }
    public String getRentOutProcessedBy() {
        return rentOutProcessedBy;
    }

    public String getReturnProcessedBy() {
        return returnProcessedBy;
    }

    public void setReturnProcessedBy(String returnProcessedBy) {
        this.returnProcessedBy = returnProcessedBy;
    }

    public void setRentOutProcessedBy(String rentOutProcessedBy) {
        this.rentOutProcessedBy = rentOutProcessedBy;
    }
    public Date getRentedOutDate() {
        return rentedOutDate;
    }

    public void setRentedOutDate(Date rentedOutDate) {
        this.rentedOutDate = rentedOutDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "RentalDto{" +
                "rentalLocation='" + rentalLocation + '\'' +
                ", rentOutProcessedBy='" + rentOutProcessedBy + '\'' +
                ", returnProcessedBy='" + returnProcessedBy + '\'' +
                ", rentedOutDate=" + rentedOutDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
