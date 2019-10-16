package com.ics.project.controllers.exceptions;

public class ResourceNotFoundException extends Exception {
    private Long id;
    private String resource;
    private String value;

    public static ResourceNotFoundException createWith(Long id, String resource) {
        return new ResourceNotFoundException(id, resource);
    }

    public static ResourceNotFoundException createWith(String value, String resource) {
        return new ResourceNotFoundException(value, resource);
    }

    private ResourceNotFoundException(String value, String resource) {
        this.value = value;
        this.resource = resource;
    }

    ResourceNotFoundException(Long id, String resource) {
        this.id = id;
        this.resource = resource;
    }

    @Override
    public String getMessage() {
        if (id != null) {
            return resource + " with id '" + id + "' not found";
        }

        return resource + " " + value + " not found";
    }

}
