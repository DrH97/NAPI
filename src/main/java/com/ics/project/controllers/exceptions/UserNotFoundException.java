package com.ics.project.controllers.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {

    public static UserNotFoundException createWith(Long id) {
        return new UserNotFoundException(id, "User");
    }

    private UserNotFoundException(Long id, String resource) {
        super(id, resource);
    }

}
