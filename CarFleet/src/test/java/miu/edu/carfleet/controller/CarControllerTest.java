package miu.edu.carfleet.controller;

import io.restassured.RestAssured;
import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.dto.CarDto;
import org.apache.coyote.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class CarControllerTest {

    private static final String LICENSE_PLATE = "ABC123";
    private static final CarDto TEST_CAR = new CarDto(LICENSE_PLATE, "Sedan", "Honda", 45000.0, Availability.AVAILABLE);

    @BeforeClass
    public static void setup() {
        RestAssured.port = 8081;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1";
    }

    @Test
    public void createCarTest() {

        given()
                .contentType("application/json")
                .body(TEST_CAR)
                .when().post("/cars").then()
                .log().all()
                .statusCode(201);

        given()
                .when()
                .get("cars/ABC123")
                .then()
                .statusCode(200)
                .body("licensePlate", equalTo("ABC123"));

        given()
                .when()
                .delete("cars/ABC123");
    }
}
