package io.github.felipepedrosa.bibliotecabackend.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
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

        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        standardError.setPath(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toString());
        standardError.setDescription("Your request have some validation issues!");
        standardError.setMessages(errors);

        return ResponseEntity.badRequest().body(standardError);
    }

}
