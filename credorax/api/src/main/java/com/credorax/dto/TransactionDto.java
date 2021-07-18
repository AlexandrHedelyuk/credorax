package com.credorax.dto;

import com.credorax.entity.Transaction;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class TransactionDto {

    @NotNull
    private Long invoice;
    @Positive
    private BigDecimal amount;
    @NotBlank
    private String currency;
    @Valid
    @NotNull
    private CardHolderDto cardHolder;
    @Valid
    @NotNull
    private PaymentCardDto card;

    public static TransactionDto valueOf(Transaction transaction) {
        return new TransactionDto()
                .setAmount(transaction.getAmount())
                .setInvoice(transaction.getInvoiceNumber())
                .setCurrency(transaction.getCurrency());
    }

}
