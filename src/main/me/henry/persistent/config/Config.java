package main.me.henry.persistent.config;

import main.me.henry.persistent.datum.Datum;
import main.me.henry.persistent.datum.DatumType;
import main.me.henry.persistent.exceptions.InvalidDatumNameException;
import main.me.henry.persistent.exceptions.InvalidDatumTypeException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public abstract class Config {
    public Config(String name, File location, ConfigFormat format) {
        HashMap<String, ArrayList<String>> locations_names = new HashMap<>();

        for(Field field : this.getClass().getDeclaredFields()) {
            Datum fieldMeta;
            if(!field.isAnnotationPresent(Datum.class)) {
                continue;
            } else {
                fieldMeta = field.getAnnotation(Datum.class);
            }
            if(fieldMeta.name() == null || fieldMeta.name().equals("")) {
                throw new InvalidDatumNameException("The datum name \"" + fieldMeta.name() + "\" was blank or null.");
            } else if(fieldMeta.name().contains(" ")) {
                throw new InvalidDatumNameException("The datum name \"" + fieldMeta.name() + "\" contained illegal space characters.");
            } else if(locations_names.containsKey(fieldMeta.location().toLowerCase()) && locations_names.get(fieldMeta.location().toLowerCase()).contains(fieldMeta.name())) {
                throw new InvalidDatumNameException("The datum name \"" + fieldMeta.name() + "\" already exists in the specified location.");
            }
            if(fieldMeta.type().equals(DatumType.INTEGER)) {
                if(!(field.getType().equals(int.class) || field.getType().equals(Integer.class))) {
                    throw new InvalidDatumTypeException("The datum type \"" + fieldMeta.type().getType().getName() + "\" cannot be applied to field \"" + field.getType().getName() + "\".");
                }
            } else {
                if(!field.getType().equals(fieldMeta.type().getType())) {
                    throw new InvalidDatumTypeException("The datum type \"" + fieldMeta.type().getType().getName() + "\" cannot be applied to field \"" + field.getType().getName() + "\".");
                }
            }



            if(!locations_names.containsKey(fieldMeta.location().toLowerCase())) {
                locations_names.put(fieldMeta.location().toLowerCase(), new ArrayList<>());
            }
            locations_names.get(fieldMeta.location().toLowerCase()).add(fieldMeta.name());
        }





        File configLocation = new File(location.getPath() + "/" + name + "." + format.getExtension().toLowerCase());
        System.out.println(configLocation);
        System.out.println(configLocation.toPath());
        if(!configLocation.exists()) {
            // make config default values here
        }


    }

    public void save() {

    }

    public void load() {

    }
}
