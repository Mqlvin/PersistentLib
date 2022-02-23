package main.me.henry.persistent.config;

import main.me.henry.persistent.datum.Datum;
import main.me.henry.persistent.exceptions.InvalidDatumTypeException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ConfigHandler {
    private ArrayList<Config> configs = new ArrayList<>();

    public void registerConfig(Config clazz) {
        for(Field f : clazz.getClass().getDeclaredFields()) {
            if(!f.isAnnotationPresent(Datum.class)) {
                continue;
            }
            Datum fieldInfo = f.getAnnotation(Datum.class);
            System.out.println(Modifier.toString(f.getModifiers()));
            if(!f.getType().equals(fieldInfo.type().getType())) {
                throw new InvalidDatumTypeException("The datum type \"" + fieldInfo.type().name().toLowerCase() + "\" cannot be applied to field \"" + f.getType().getName() + "\"");
            }
        }
    }


}
