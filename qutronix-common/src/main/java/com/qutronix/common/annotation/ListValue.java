package com.qutronix.common.annotation;

import com.qutronix.common.validator.constraintvalidators.ListValueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/4/25 17:13
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ListValueConstraintValidator.class}
)
public @interface ListValue {
    String message() default "{com.qutronix.common.annotation.ListValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] vals() default{};
}
