package miu.edu.carrental.data;

import miu.edu.carrental.dto.CreditCardDto;
import miu.edu.carrental.dto.CustomerDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CustomersData {
    private static List<CustomerDto> customers = Arrays.asList(
            new CustomerDto(10001L, "John Doe", "johndoe@example.com", new CreditCardDto("1111 2222 3333 4444", "Visa", getDate("08/25"))),
            new CustomerDto(10002L, "Jane Smith", "janesmith@example.com", new CreditCardDto("2222 3333 4444 5555", "Mastercard", getDate("12/23"))),
            new CustomerDto(10003L, "Bob Johnson", "bobjohnson@example.com", new CreditCardDto("3333 4444 5555 6666", "Amex", getDate("05/24"))),
            new CustomerDto(10004L, "Sara Lee", "saralee@example.com", new CreditCardDto("4444 5555 6666 7777", "Discover", getDate("02/27")))
    );

    private static Date getDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<CustomerDto> getCustomers() {
        return customers;
    }
}
