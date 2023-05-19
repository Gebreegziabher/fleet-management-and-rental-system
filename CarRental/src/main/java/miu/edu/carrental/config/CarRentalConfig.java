package miu.edu.carrental.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="car-rental")
public class CarRentalConfig {
    private ReservationSetting reservationSetting;

    public ReservationSetting getReservationSetting() {
        return reservationSetting;
    }

    public void setReservationSetting(ReservationSetting reservationSetting) {
        this.reservationSetting = reservationSetting;
    }

    public CarRentalConfig(){}

    public static class ReservationSetting {
        private int maxAllowedReservations;
        private double paymentAmountPerDay;

        public int getMaxAllowedReservations() {
            return maxAllowedReservations;
        }

        public void setMaxAllowedReservations(int maxAllowedReservations) {
            this.maxAllowedReservations = maxAllowedReservations;
        }

        public double getPaymentAmountPerDay() {
            return paymentAmountPerDay;
        }

        public void setPaymentAmountPerDay(double paymentAmountPerDay) {
            this.paymentAmountPerDay = paymentAmountPerDay;
        }
    }
}
