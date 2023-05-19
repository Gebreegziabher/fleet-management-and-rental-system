package miu.edu.carrental.adapter;

import miu.edu.carrental.domain.Payment;
import miu.edu.carrental.dto.reservation.PaymentDto;

public class PaymentAdapter {
    public static PaymentDto getPaymentDtoFromPayment(Payment payment) {
        return new PaymentDto(payment.getId(), payment.getAmount(), payment.getPaymentDate(), payment.getPaymentStatus());
    }

    public static Payment getPaymentFromPaymentDto(PaymentDto paymentDto) {
        return new Payment(paymentDto.getId(), paymentDto.getAmount(), paymentDto.getPaymentDate(), paymentDto.getPaymentStatus());
    }
}
