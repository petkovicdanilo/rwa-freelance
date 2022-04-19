package com.github.petkovicdanilo.freelance.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {

    @Schema(description = "Type of error", example = "NOT_FOUND")
    ErrorType errorType;

    @Schema(description = "Type of resource for which error occurred", example = "JOB")
    ResourceType resourceType;

    @Schema(description = "Additional message for error", example = "Job with given `id` was not found")
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
