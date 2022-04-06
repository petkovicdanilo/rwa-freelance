package com.github.petkovicdanilo.freelance.exception;

import lombok.Getter;

@Getter
public abstract class ResourceException extends Exception{

    protected ErrorInfo.ResourceType resourceType;

    public ResourceException(ErrorInfo.ResourceType resourceType, String message) {
        super(message);
        this.resourceType = resourceType;
    }
}
