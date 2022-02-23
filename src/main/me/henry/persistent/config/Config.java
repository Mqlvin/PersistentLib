package main.me.henry.persistent.config;

import java.io.File;
import java.io.IOException;

public abstract class Config {
    public Config(String name, File location, ConfigFormat format) {
        File configLocation = new File(location.getPath() + "/" + name + "." + format.getExtension().toLowerCase());
        System.out.println(configLocation);
        System.out.println(configLocation.toPath());
        if(!configLocation.exists()) {
            try {
                configLocation.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {

    }

    public void load() {

    }
}
