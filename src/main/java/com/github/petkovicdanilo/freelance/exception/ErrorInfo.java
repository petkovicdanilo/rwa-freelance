package com.github.petkovicdanilo.freelance.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {

    ErrorType errorType;
    ResourceType resourceType;

    String message;

    public enum ErrorType {
        BAD_REQUEST,
        NOT_FOUND,
        UNKNOWN,
        UNIQUE_VIOLATION,
    }

    public enum ResourceType {
        JOB,
        TECHNOLOGY,
        USER,
    }

}
