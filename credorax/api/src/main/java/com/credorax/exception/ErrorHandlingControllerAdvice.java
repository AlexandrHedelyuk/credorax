package com.credorax.exception;

import com.credorax.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        e.getConstraintViolations().forEach(violation -> response.getErrors().put(violation.getPropertyPath().toString(), violation.getMessage()));
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> response.getErrors().put(formatFieldName(fieldError.getField()), fieldError.getDefaultMessage()));
        return response;
    }

    private String formatFieldName(String fieldName) {
        String[] parts = fieldName.split("\\.");
        return parts[parts.length - 1];
    }
}
