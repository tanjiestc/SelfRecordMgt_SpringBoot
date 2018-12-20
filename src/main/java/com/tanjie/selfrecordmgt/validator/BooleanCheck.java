package com.tanjie.selfrecordmgt.validator;


import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BooleanCheckClass.class)  // 关联解析类
@Target({ElementType.METHOD,ElementType.FIELD})  // 注解作用于方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface BooleanCheck {
    String message() default "请输入一个布尔值！";

    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};
}
