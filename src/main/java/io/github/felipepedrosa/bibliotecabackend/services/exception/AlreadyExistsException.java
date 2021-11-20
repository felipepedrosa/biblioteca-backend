package io.github.felipepedrosa.bibliotecabackend.services.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
