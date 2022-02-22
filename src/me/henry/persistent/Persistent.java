package me.henry.persistent;

import me.henry.persistent.config.Config;
import me.henry.persistent.config.ConfigHandler;

import java.util.ArrayList;

public class Persistent {
    private final ConfigHandler configs = new ConfigHandler();

    public void register(Config config) {
        configs.registerConfig(config);
    }
}
