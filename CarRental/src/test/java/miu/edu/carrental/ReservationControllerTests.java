package miu.edu.carrental;

import io.restassured.RestAssured;
import miu.edu.carrental.dto.reservation.RentalDto;
import miu.edu.carrental.dto.reservation.ReservationRequestDto;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class ReservationControllerTests {
    private static final String RESERVATIONS_ENDPOINT = "/reservations";
    private static final String RENTAL_ENDPOINT = "/reservations/{id}/rental";
    private static final String RETURN_ENDPOINT = "/reservations/{id}/return";

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1/";
    }

    @Test
    public void testReserveEndpoint() {
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto();
        reservationRequestDto.setCustomerNumber(1L);
        reservationRequestDto.setLicensePlate("ABC-123");
        reservationRequestDto.setStartingOn(new Date());
        reservationRequestDto.setEndingOn(new Date());

        given()
                .contentType("application/json")
                .body(reservationRequestDto)
                .when()
                .post(RESERVATIONS_ENDPOINT)
                .then()
                .statusCode(200)
                .body("car.licensePlate", equalTo("ABC-123"))
                .body("customer.id", equalTo(1))
                .body("startingOn", equalTo("2023-05-01"))
                .body("endingOn", equalTo("2023-05-10"));
    }

    @Test
    public void testRentOutEndpoint() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentedOutDate(new Date());
        rentalDto.setReturnedDate(new Date());

        given()
                .contentType("application/json")
                .pathParam("id", 1)
                .body(rentalDto)
                .when()
                .post(RENTAL_ENDPOINT)
                .then()
                .statusCode(200)
                .body("car.licensePlate", equalTo("ABC-123"))
                .body("customer.id", equalTo(1))
                .body("rentedOn", equalTo("2023-05-01"))
                .body("returnedOn", equalTo("2023-05-10"));
    }

    @Test
    public void testReturnAndPayEndpoint() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentedOutDate(new Date());
        rentalDto.setReturnedDate(new Date());

        given()
                .contentType("application/json")
                .pathParam("id", 1)
                .body(rentalDto)
                .when()
                .put(RETURN_ENDPOINT)
                .then()
                .statusCode(200)
                .body("car.licensePlate", equalTo("ABC-123"))
                .body("customer.id", equalTo(1))
                .body("rentedOn", equalTo("2023-05-01"))
                .body("returnedOn", equalTo("2023-05-10"));
    }
}