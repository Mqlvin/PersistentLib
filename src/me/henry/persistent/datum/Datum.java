package me.henry.persistent.datum;

import me.henry.persistent.datum.DatumType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Datum {
    String name();
    String location();
    String description() default "";
    DatumType type();
}