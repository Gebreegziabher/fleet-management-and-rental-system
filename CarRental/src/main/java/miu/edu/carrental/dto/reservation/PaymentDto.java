package miu.edu.carrental.dto.reservation;

import miu.edu.carrental.common.PaymentStatus;

import java.util.Date;

public class PaymentDto {
    private long id;
    private double amount;
    private Date paymentDate;
    private PaymentStatus paymentStatus;

    public PaymentDto() {
    }

    public PaymentDto(long id, double amount, Date paymentDate, PaymentStatus paymentStatus) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
