package com.anastasia.smart_service.domain.strategy;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Strategy {

    @AliasFor(annotation = Component.class)
    String value() default "";

    String name();

    String description() default "";
}