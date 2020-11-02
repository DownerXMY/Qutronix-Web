package com.qutronix.common.validator.constraintvalidators;

import com.qutronix.common.annotation.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/4/25 17:40
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {
    private Set<Integer> set=new HashSet<>();
    @Override
    public void initialize(ListValue constraintAnnotation) {
        if(constraintAnnotation.vals()!=null && constraintAnnotation.vals().length>0){
            for(Integer value:constraintAnnotation.vals()){
                set.add(value);
            }
        }

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
