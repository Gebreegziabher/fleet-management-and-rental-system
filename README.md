# Car Fleet Management and Rental System

This is a project of light-weight Spring Boot apps used to manage and maintain list of cars and allow customers to search, reserve and rent cars.

## Functionalities

**Car functionality**
 * We can add, remove, update and find cars in the car rental application. You can search cars on car type, brand, price. We can have multiple cars of the same brand and type. For example we have 15 Ford F1 Pickup trucks. Every individual car has a unique license plate.
 * The system keeps track of how many cars of a certain brand and type are available in the car fleet.

**Customer functionality**
 * We can add, remove, update and find customers in the car rental application. 
 * You can search customers on customernumber, name and email address. Every customers has an unique customernumber.

**Reservation functionality**
 * A customer can reserve a certain car type for a certain period. 
 * Borrowing functionality.
 * A customer can pickup the car at the car renting location
 * A customer can return the car to the car renting location
 * The customer pays for the car when the car is returned.
 * Rentals can only be paid by creditcard. 

The system consist of 2 separate applications: 

1. **Car Fleet Application** 
  * Contains the collection of cars with all car functionality described above. This functionality can be accessed through a REST interface. Write a REST client that calls the functionality of this application.
  * Uses a Mongo database 
2. **Car Rental Application**
  * Contains all functionality that a customer needs in order to search and reserve a car. It also contains the functionality for the rental employee to handle car pickups and car returns. This
functionality can be accessed through a REST interface.
  * Uses a HSQLDB database, Use JPA for database access
  * Uses logging (console and file) to at different logging levels. Logs are monitored with Grafana.
  * Uses scheduling so that the application prints every 20 seconds an overview of all cars including their state (rented out, available).
  * Uses JUnit and RestAssured for testing
  * Anytime a car is reserved, the car rental application sends a JMS message using ActiveMQ to the car fleet application. The car fleet keeps track of how many cars are available of a certain brand/type.

## Technologies used

* SpringBoot
* ActiveMQ
* Mongo DB
* Hsql BD
* Spring Web

![image](https://github.com/Gebreegziabher/fleet-management-and-rental-system/assets/6954726/593befc7-64c9-4610-9eac-8e2d2b73f8d9)
