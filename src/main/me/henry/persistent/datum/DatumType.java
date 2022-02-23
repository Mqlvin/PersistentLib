package main.me.henry.persistent.datum;

public enum DatumType {
    BOOLEAN(Boolean.class),
    STRING(String.class),
    INTEGER(Integer.class);

    private final Class<?> type;
    DatumType(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return this.type;
    }
}
