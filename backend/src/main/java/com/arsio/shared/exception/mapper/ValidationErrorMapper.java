package com.arsio.shared.exception.mapper;

import com.arsio.shared.exception.model.FieldValidationError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationErrorMapper {

    public List<FieldValidationError> map(MethodArgumentNotValidException ex) {

        assert ex.getBindingResult() != null;
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldValidationError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();
    }

    public List<FieldValidationError> map(ConstraintViolationException ex) {

        return ex.getConstraintViolations()
                .stream()
                .map(v -> new FieldValidationError(
                        v.getPropertyPath().toString(),
                        v.getMessage()))
                .toList();
    }
}
