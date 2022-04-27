package com.github.petkovicdanilo.freelance.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class ErrorInfo {

    @Schema(description = "Type of error", example = "NOT_FOUND")
    ErrorType errorType;

    @Schema(description = "Type of resource for which error occurred", example = "JOB")
    ResourceType resourceType;

    @Schema(description = "Additional message for error", example = "Job with given `id` was not found")
    String message;

    @Schema(description = "More detailed information about errors")
    Map<String, String> details;

    public enum ErrorType {
        BAD_REQUEST,
        NOT_FOUND,
        UNKNOWN,
        UNIQUE_VIOLATION,
        VALIDATION,
    }

    public enum ResourceType {
        JOB,
        TECHNOLOGY,
        USER,
        APPLICATION,
    }

}
