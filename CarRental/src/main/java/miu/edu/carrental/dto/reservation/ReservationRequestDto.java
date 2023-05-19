package miu.edu.carrental.dto.reservation;

import java.util.Date;

public class ReservationRequestDto {
    private Date startingOn;
    private Date endingOn;
    private long customerNumber;
    private String licensePlate;

    public ReservationRequestDto() {
    }

    public ReservationRequestDto(Date startingOn,
                                 Date endingOn,
                                 long customerNumber,
                                 String licensePlate
    ) {
        this.startingOn = startingOn;
        this.endingOn = endingOn;
        this.customerNumber = customerNumber;
        this.licensePlate = licensePlate;
    }

    public Date getStartingOn() {
        return startingOn;
    }

    public void setStartingOn(Date startingOn) {
        this.startingOn = startingOn;
    }

    public Date getEndingOn() {
        return endingOn;
    }

    public void setEndingOn(Date endingOn) {
        this.endingOn = endingOn;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
