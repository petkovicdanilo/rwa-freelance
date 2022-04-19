package com.github.petkovicdanilo.freelance.exception;

import com.github.petkovicdanilo.freelance.model.api.ErrorInfo;

public class UniqueViolationException extends ResourceException {
    public UniqueViolationException(ErrorInfo.ResourceType resourceType, String message) {
        super(resourceType, message);
    }
}
