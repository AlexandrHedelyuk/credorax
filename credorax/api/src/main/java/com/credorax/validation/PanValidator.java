package com.credorax.validation;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PanValidator implements ConstraintValidator<Pan, String> {

    @Override
    public void initialize(Pan contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        if (ObjectUtils.isEmpty(contactField)) return false;
        return checkLuhn(contactField);
    }


    private boolean checkLuhn(String cardNumber) {
        int digitsCount = cardNumber.length();
        if (digitsCount < 16) return false;
        int sum = 0;
        boolean isSecond = false;
        for (int i = digitsCount - 1; i >= 0; i--) {

            int d = cardNumber.charAt(i) - '0';
            if (isSecond)
                d = d * 2;

            sum += d / 10;
            sum += d % 10;
            isSecond = !isSecond;
        }
        return (sum % 10 == 0);
    }
}
