package test.configs;

import main.me.henry.persistent.config.Config;
import main.me.henry.persistent.config.ConfigFormat;
import main.me.henry.persistent.datum.Datum;
import main.me.henry.persistent.datum.DatumType;

import java.io.File;

public class MyYamlConfig extends Config {
    @Datum(name = "newValue",
            location = "",
            description = "This is my new value.",
            type = DatumType.INTEGER)
    public Integer myString = 5;

    @Datum(name = "newValueA",
            location = "",
            description = "This is my new value.",
            type = DatumType.INTEGER)
    private static Integer myStringHi = 5;

    public MyYamlConfig(String name, File location, ConfigFormat format) {
        super(name, location, format);
    }
}
