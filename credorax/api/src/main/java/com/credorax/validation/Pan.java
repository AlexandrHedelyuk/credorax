package com.credorax.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PanValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pan {

    String message() default "Invalid pan number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
