package main.me.henry.persistent.datum;

import java.lang.reflect.Field;

public class DatumPlaceholder {
    private Datum meta;
    private Field field;

    public DatumPlaceholder(Datum datum, Field field) {
        this.meta = datum;
        this.field = field;
    }

    public Datum getMeta() {
        return meta;
    }

    public Field getField() {
        return field;
    }
}
