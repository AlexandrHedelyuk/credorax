package com.credorax.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpiryDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpiryDate {

    String message() default "Invalid expiry date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
