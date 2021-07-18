package com.credorax.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class CardHolderDto {

    @NotBlank
    public String name;
    @Email
    @NotBlank
    public String email;
}
