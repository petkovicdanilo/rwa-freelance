package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.exception.BadRequestException;
import com.github.petkovicdanilo.freelance.exception.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@ResponseBody
public class ErrorController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorInfo handleBadRequestException(BadRequestException e) {
        return ErrorInfo.builder()
                .errorType(ErrorInfo.ErrorType.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(UniqueViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorInfo handleUniqueViolationException(UniqueViolationException e) {
        return ErrorInfo.builder()
                .errorType(ErrorInfo.ErrorType.UNIQUE_VIOLATION)
                .resourceType(e.getResourceType())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorInfo handleResourceNotFoundException(ResourceNotFoundException e) {
        return ErrorInfo.builder()
                .errorType(ErrorInfo.ErrorType.NOT_FOUND)
                .resourceType(e.getResourceType())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorInfo handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ErrorInfo.builder()
                .errorType(ErrorInfo.ErrorType.BAD_REQUEST)
                .message("Mismatched type")
                .build();
    }
}
