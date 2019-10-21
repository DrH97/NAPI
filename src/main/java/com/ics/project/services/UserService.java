package com.ics.project.services;

import com.ics.project.controllers.exceptions.UserExistsException;
import com.ics.project.controllers.exceptions.UserNotFoundException;
import com.ics.project.models.User;

import java.util.List;

/**
 * @author Dr H
 */
public interface UserService {
    User create(User user) throws UserExistsException;

    List<User> getAllUsers();

    User me(Long id) throws UserNotFoundException;

    User byIdNumber(String idNumber) throws UserNotFoundException;
}
