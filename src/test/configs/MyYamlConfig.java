package test.configs;

import main.me.henry.persistent.config.Config;
import main.me.henry.persistent.config.ConfigFormat;
import main.me.henry.persistent.datum.Datum;
import main.me.henry.persistent.datum.DatumType;

import java.io.File;

public class MyYamlConfig extends Config {
    @Datum(name = "haveUppercase",
            location = "password/formatting/cool/this/is/another/formatting",
            description = "Should passwords have uppercase.",
            type = DatumType.BOOLEAN)
    public static boolean shouldHaveUppercase = false;

    @Datum(name = "haveSpecialCharacters",
            location = "password/formatting/",
            description = "Should passwords contain special characters.",
            type = DatumType.BOOLEAN)
    public static Boolean shouldHaveSpecialCharacters = true;

    @Datum(name = "passwordLength",
            location = "",
            description = "The passwords length.",
            type = DatumType.INTEGER)
    public static Integer passwordLength = 16;

    public MyYamlConfig(String name, File location, ConfigFormat format) {
        super(name, location, format);
    }
}
