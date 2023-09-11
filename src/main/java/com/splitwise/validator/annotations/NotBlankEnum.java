package com.splitwise.validator.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


/**
 * Includes not null and not empty
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
@Target(ElementType.FIELD)
public @interface NotBlankEnum {
    String message() default "Empty or Null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum<?>> enumClass();

}
