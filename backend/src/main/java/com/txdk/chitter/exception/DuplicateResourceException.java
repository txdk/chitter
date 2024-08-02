package com.txdk.chitter.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String name, Class<?> entity) {
        super("Unable to create " + entity.getSimpleName().toLowerCase() + " with name " + name);
    }
    
}
