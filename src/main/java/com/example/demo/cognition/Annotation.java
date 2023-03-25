package com.example.demo.cognition;

import java.lang.annotation.*;

public class Annotation {
    @MyAnnotation
    public static void main(String[] args) {

    }
}

@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation{
    //注解的参数：参数类型 + 参数名();
    String name() default "";
    String[] desc() default {""};
}