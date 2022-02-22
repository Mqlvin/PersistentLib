package me.henry.persistent.exceptions;

public class InvalidDatumTypeException extends RuntimeException {
    public InvalidDatumTypeException(String error) {
        super(error);
    }
}
