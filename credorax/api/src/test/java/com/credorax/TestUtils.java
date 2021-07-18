package com.credorax;

import com.credorax.dto.CardHolderDto;
import com.credorax.dto.PaymentCardDto;
import com.credorax.dto.TransactionDto;
import com.credorax.response.ValidationErrorResponse;

import java.math.BigDecimal;

public class TestUtils {

    public static TransactionDto getDtoDataForTest() {

        TransactionDto dto = new TransactionDto();
        dto.setCurrency("EUR")
                .setInvoice(1L)
                .setAmount(BigDecimal.TEN);
        CardHolderDto cardHolder = new CardHolderDto()
                .setName("name")
                .setEmail("email@test.com");
        PaymentCardDto card = new PaymentCardDto()
                .setExpiry("0222")
                .setPan("4111111111111111")
                .setCvv("789");
        return dto.setCardHolder(cardHolder)
                .setCard(card);
    }

    public static ValidationErrorResponse getValidationErrorResponseWithIncorrectNameAndPan() {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.getErrors().put("name", "must not be blank");
        response.getErrors().put("pan", "Invalid pan number");
        return response;
    }

    public static ValidationErrorResponse getValidationErrorResponseWithIncorrectExpiryDate() {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.getErrors().put("expiry", "Invalid expiry date");
        return response;
    }

    public static ValidationErrorResponse getValidationErrorResponseWithIncorrectAmount() {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.getErrors().put("amount", "must be greater than 0");
        return response;
    }

    public static ValidationErrorResponse getValidationErrorResponseWithMissedInvoiceAndNameAndEmail() {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.getErrors().put("name", "must not be blank");
        response.getErrors().put("email", "must not be blank");
        response.getErrors().put("invoice", "must not be null");
        return response;
    }

    public static ValidationErrorResponse getValidationErrorResponseWithIncorrectEmail() {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.getErrors().put("email", "must be a well-formed email address");
        return response;
    }
}
