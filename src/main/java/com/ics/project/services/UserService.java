package com.ics.project.services;

import com.ics.project.controllers.exceptions.UserNotFoundException;
import com.ics.project.models.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAllUsers();

    User me(Long id) throws UserNotFoundException;
}
