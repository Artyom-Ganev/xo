package ru.ganev.xo.exception;

public class AlreadyExistException extends IllegalArgumentException {

    public AlreadyExistException(String s) {
        super(s);
    }
}
