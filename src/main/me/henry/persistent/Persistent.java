package main.me.henry.persistent;

import main.me.henry.persistent.config.Config;
import main.me.henry.persistent.config.ConfigHandler;

public class Persistent {
    private final ConfigHandler configs = new ConfigHandler();

    public void register(Config config) {
        configs.registerConfig(config);
    }
}
