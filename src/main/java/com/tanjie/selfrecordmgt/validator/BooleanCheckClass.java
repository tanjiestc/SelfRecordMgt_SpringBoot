package com.tanjie.selfrecordmgt.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BooleanCheckClass implements ConstraintValidator<BooleanCheck, Boolean> {


    @Override
    public void initialize(BooleanCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        System.out.println(value);

        return false;
    }
}