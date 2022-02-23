package main.me.henry.persistent.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import main.me.henry.persistent.datum.Datum;
import main.me.henry.persistent.datum.DatumPlaceholder;
import main.me.henry.persistent.datum.DatumType;
import main.me.henry.persistent.exceptions.InvalidDatumNameException;
import main.me.henry.persistent.exceptions.InvalidDatumTypeException;
import main.me.henry.persistent.exceptions.InvalidModifiersException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class Config {
    private HashMap<String, ArrayList<DatumPlaceholder>> locations_names = new HashMap<>();
    public Config(String name, File location, ConfigFormat format) {
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
            } else if(fieldMeta.type().equals(DatumType.BOOLEAN)) {
                if(!(field.getType().equals(boolean.class) || field.getType().equals(Boolean.class))) {
                    throw new InvalidDatumTypeException("The datum type \"" + fieldMeta.type().getType().getName() + "\" cannot be applied to field \"" + field.getType().getName() + "\".");
                }
            } else {
                if (!field.getType().equals(fieldMeta.type().getType())) {
                    throw new InvalidDatumTypeException("The datum type \"" + fieldMeta.type().getType().getName() + "\" cannot be applied to field \"" + field.getType().getName() + "\".");
                }
            }
            ArrayList<String> modifiers = new ArrayList<>(Arrays.asList(Modifier.toString(field.getModifiers()).toLowerCase().split(" ")));
            if(modifiers.contains("private")) {
                throw new InvalidModifiersException("Declared datum \"" + fieldMeta.name() + " contains illegal modifier \"private\".");
            } else if(!modifiers.contains("public")) {
                throw new InvalidModifiersException("Declared datum \"" + fieldMeta.name() + "\" requires the \"public\" modifier.");
            } else if(!modifiers.contains("static")) {
                throw new InvalidModifiersException("Declared datum \"" + fieldMeta.name() + "\" requires the \"static\" modifier.");
            } else if(modifiers.contains("final")) {
                throw new InvalidModifiersException("Declared datum \"" + fieldMeta.name() + "\" contains illegal modifier \"final\".");
            }



            if(!locations_names.containsKey(fieldMeta.location().toLowerCase())) {
                locations_names.put(fieldMeta.location().toLowerCase(), new ArrayList<>());
            }
            locations_names.get(fieldMeta.location().toLowerCase()).add(new DatumPlaceholder(fieldMeta, field));
        }





        File configLocation = new File(location.getPath() + "/" + name + "." + format.getExtension().toLowerCase());
        System.out.println(configLocation);
        System.out.println(configLocation.toPath());
        if(!configLocation.exists()) {
            save();
        }


    }

    public void save() {
        JsonObject saveObject = new JsonObject();

        for(String loc : locations_names.keySet()) {
            ArrayList<String> splitPath = new ArrayList<>(Arrays.asList(loc.split("/")));
            splitPath.removeIf(s -> s == null || s.equals("") || s.equals(" "));
            Collections.reverse(splitPath);
            boolean inMain = true;
            String locName = "";
            if(splitPath.size() != 0) {
                locName = splitPath.get(0);
                splitPath.remove(0);
                inMain = false;
            }

            ArrayList<DatumPlaceholder> datums = locations_names.get(loc);
            JsonObject object = new JsonObject();
            for(DatumPlaceholder datum : datums) {
                try {
                    if(inMain) {
                        saveObject.addProperty(datum.getMeta().name(), String.valueOf(datum.getField().get(datum.getField().getType())));
                    } else {
                        object.addProperty(datum.getMeta().name(), String.valueOf(datum.getField().get(datum.getField().getType())));
                    }
                } catch(IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(inMain) {
                continue;
            }

            JsonObject newObj = object;
            for(String f : splitPath) {
                JsonObject n = new JsonObject();
                n.add(f, newObj);
                newObj = n;
                System.out.println(newObj);
            }
            // merge existing
            saveObject.add(locName, newObj);
        }
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(saveObject));
    }

    public void load() {

    }
}
