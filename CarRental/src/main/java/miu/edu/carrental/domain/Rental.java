package miu.edu.carrental.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rentingLocation;
    private String rentOutProcessedBy;//Name of rental employee
    private String returnProcessedBy;//Name of rental employee
    @Temporal(value = TemporalType.DATE)
    @NotNull
    private Date rentedOutDate;
    @Temporal(value = TemporalType.DATE)
    private Date returnedDate;

    public Rental() {
    }

    public Rental(long id, String rentingLocation, String rentOutProcessedBy, String returnProcessedBy, Date rentedOutDate, Date returnedDate) {
        this.id = id;
        this.rentingLocation = rentingLocation;
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

    public String getRentingLocation() {
        return rentingLocation;
    }

    public void setRentingLocation(String rentingLocation) {
        this.rentingLocation = rentingLocation;
    }

    public String getRentOutProcessedBy() {
        return rentOutProcessedBy;
    }

    public void setRentOutProcessedBy(String rentOutProcessedBy) {
        this.rentOutProcessedBy = rentOutProcessedBy;
    }

    public String getReturnProcessedBy() {
        return returnProcessedBy;
    }

    public void setReturnProcessedBy(String returnProcessedBy) {
        this.returnProcessedBy = returnProcessedBy;
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
        return "Rental{" +
                "rentingLocation='" + rentingLocation + '\'' +
                ", rentOutProcessedBy='" + rentOutProcessedBy + '\'' +
                ", returnProcessedBy='" + returnProcessedBy + '\'' +
                ", rentedOutDate=" + rentedOutDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
