package miu.edu.carrental.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class CreditCardDto {
    private String cardNumber;
    private String type;

    private Date validationDate;

    public CreditCardDto() {
    }

    public CreditCardDto(String cardNumber, String type, Date validationDate) {
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
