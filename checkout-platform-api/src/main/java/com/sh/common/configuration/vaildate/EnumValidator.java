package com.sh.common.configuration.vaildate;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author huisheng.jin
 * @version 2019/11/28.
 */
@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValid, String> {

    // 枚举校验注解
    private EnumValid annotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {

        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;

        Class<?> cls = annotation.target();
        boolean ignoreEmpty = annotation.ignoreEmpty();

        // target为枚举，并且value有值，或者不忽视空值，才进行校验
        if (cls.isEnum() && (StringUtils.isNotEmpty(value) || !ignoreEmpty)) {

            Object[] objects = cls.getEnumConstants();
            try {
                Method method = cls.getMethod("name");
                for (Object obj : objects) {
                    Object code = method.invoke(obj);
                    if (value.equals(code.toString())) {
                        result = true;
                        break;
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.warn("EnumValidator call isValid() method exception.");
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }
}
