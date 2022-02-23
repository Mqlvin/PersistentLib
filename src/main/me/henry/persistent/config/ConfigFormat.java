package main.me.henry.persistent.config;

public enum ConfigFormat {
    YAML("yaml"),
    JSON("json");

    private final String extension;

    ConfigFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
