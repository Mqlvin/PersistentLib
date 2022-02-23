package main.me.henry.persistent.exceptions;

public class InvalidDatumTypeException extends RuntimeException {
    public InvalidDatumTypeException(String message) {
        super(message);
    }
}
