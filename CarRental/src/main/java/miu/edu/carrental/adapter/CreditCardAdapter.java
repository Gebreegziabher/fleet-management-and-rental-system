package miu.edu.carrental.adapter;

import miu.edu.carrental.domain.CreditCard;
import miu.edu.carrental.dto.CreditCardDto;

public class CreditCardAdapter {
    public static CreditCardDto getCreditCardDtoFromCreditCard(CreditCard creditCard) {
        return new CreditCardDto(creditCard.getCardNumber(), creditCard.getType(), creditCard.getValidationDate());
    }

    public static CreditCard getCreditCardFromCreditCardDto(CreditCardDto creditCardDto) {
        return new CreditCard(creditCardDto.getCardNumber(), creditCardDto.getType(), creditCardDto.getValidationDate());
    }
}
