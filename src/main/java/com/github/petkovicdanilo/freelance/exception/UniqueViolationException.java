package com.github.petkovicdanilo.freelance.exception;

public class UniqueViolationException extends ResourceException {
    public UniqueViolationException(ErrorInfo.ResourceType resourceType, String message) {
        super(resourceType, message);
    }
}
