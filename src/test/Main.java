package test;

import main.me.henry.persistent.Persistent;
import main.me.henry.persistent.config.ConfigFormat;
import test.configs.MyYamlConfig;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Persistent configHandler = new Persistent();

        MyYamlConfig myConfig = new MyYamlConfig("myNewConfig", new File("./"), ConfigFormat.YAML);
        myConfig.load();


    }
}
