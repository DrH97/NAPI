package com.ics.project.controllers.exceptions;

import com.ics.project.models.User;

public class UserExistsException extends Exception {
    private User user;

    private UserExistsException(User movie) {
        this.user = user;
    }

    public static UserExistsException createWith(User user) {
        return new UserExistsException(user);
    }

    @Override
    public String getMessage() {
        return "User already exists";
    }

    public User getUser() {
        return user;
    }
}
