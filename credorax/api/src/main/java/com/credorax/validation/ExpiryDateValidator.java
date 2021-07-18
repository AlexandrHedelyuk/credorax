package com.credorax.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExpiryDateValidator implements
        ConstraintValidator<ExpiryDate, String> {

    @Override
    public void initialize(ExpiryDate contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        try {
            if (StringUtils.isEmpty(contactField)) return false;

            SimpleDateFormat sdf = new SimpleDateFormat("MMyy");
            Date parse = sdf.parse(contactField);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            return instance.after(Calendar.getInstance());
        } catch (ParseException e) {
            return false;
        }
    }
}
