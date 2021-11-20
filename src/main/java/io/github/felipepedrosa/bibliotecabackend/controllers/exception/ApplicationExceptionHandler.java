package io.github.felipepedrosa.bibliotecabackend.controllers.exception;

import io.github.felipepedrosa.bibliotecabackend.services.exception.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .filter(fieldError -> !ObjectUtils.isEmpty(fieldError.getDefaultMessage()))
                .filter(fieldError -> !fieldError.getDefaultMessage().isBlank())
                .map(fieldError -> String.format("[%s]: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .sorted()
                .collect(Collectors.toList());

        StandardError standardError = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                getRequestUrl(),
                "Your request have some validation issues!",
                errors
        );

        return ResponseEntity.badRequest().body(standardError);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardError> handleDataIntegrityViolationException(AlreadyExistsException exception) {
        StandardError standardError = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                getRequestUrl(),
                "This resource already exists",
                List.of(exception.getMessage())
        );

        return ResponseEntity.badRequest().body(standardError);
    }

    private String getRequestUrl() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().build().toString();
    }
}
