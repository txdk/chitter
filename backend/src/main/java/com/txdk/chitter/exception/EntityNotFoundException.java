package com.txdk.chitter.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' could not be found.");
    }

    public EntityNotFoundException(String name, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with name '" + name + "' could not be found.");
    }
    
}
