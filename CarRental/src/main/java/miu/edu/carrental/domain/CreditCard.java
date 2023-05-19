package miu.edu.carrental.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class CreditCard {
    @Id
    private String cardNumber;
    private String type;
    @Temporal(value = TemporalType.DATE)
    private Date validationDate;

    public CreditCard() {
    }

    public CreditCard(String cardNumber, String type, Date validationDate) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.validationDate = validationDate;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", type='" + type + '\'' +
                ", validationDate='" + validationDate + '\'' +
                '}';
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getType() {
        return type;
    }

    public Date getValidationDate() {
        return validationDate;
    }
}
