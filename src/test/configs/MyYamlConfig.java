package test.configs;

import me.henry.persistent.config.Config;
import me.henry.persistent.datum.Datum;
import me.henry.persistent.datum.DatumType;

public class MyYamlConfig extends Config {
    @Datum(name = "newValue",
            location = "",
            description = "This is my new value.",
            type = DatumType.INTEGER)
    public static int myString = 5;
}
