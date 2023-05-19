package miu.edu.carrental.util;

import java.util.Date;

public class PaymentCalculatorUtil {
    public static double getTotalPayment(Date start, Date end, double paymentPerDay) {
        double totalPayment = DateUtil.getDateDifference(start, end) * paymentPerDay;
        return totalPayment;
    }
}
