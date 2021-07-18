package com.credorax.dto;

import com.credorax.validation.ExpiryDate;
import com.credorax.validation.Pan;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;


@Data
@Accessors(chain = true)
public class PaymentCardDto {
    @Pan
    public String pan;
    @ExpiryDate
    public String expiry;
    @NotBlank
    public String cvv;

}
