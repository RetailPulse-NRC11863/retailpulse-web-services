package com.retailpulse.platform.shared.interfaces.rest;

import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import com.retailpulse.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> handleValidation(MethodArgumentNotValidException exception) {
        List<String> details = exception.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList();
        return ResponseEntity.badRequest().body(ErrorResponseAssembler.toResource("validation_error", "Invalid request", details));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResource> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(ErrorResponseAssembler.toResource("bad_request", exception.getMessage(), List.of()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResource> handleRuntime(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponseAssembler.toResource("internal_error", exception.getMessage(), List.of()));
    }
}
