package com.ics.project.services;

import com.ics.project.controllers.exceptions.UserExistsException;
import com.ics.project.controllers.exceptions.UserNotFoundException;
import com.ics.project.models.User;
import com.ics.project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dr H
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Creation of a user in the system
     *
     * @param user User
     * @return user
     * @throws UserExistsException if the user already in the system
     */
    @Override
    public User create(User user) throws UserExistsException {
        User existingUser = userRepo.findByIdNumber(user.getIdNumber().trim());

        if (existingUser != null) {
            throw UserExistsException.createWith(user);
        }

        return userRepo.save(user);
    }

    /**
     * Find all users
     * @return List of users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Find user by id
     * @param id Long
     * @return user
     * @throws UserNotFoundException if no user exists by that id
     */
    @Override
    public User me(Long id) throws UserNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> UserNotFoundException.createWith(id));
    }

    /**
     * Alternatively find user by the idNumber
     * @param idNumber String
     * @return user
     * @throws UserNotFoundException if user by that idNumber does not exist
     */
    @Override
    public User byIdNumber(String idNumber) throws UserNotFoundException {
        return userRepo.findByIdNumber(idNumber);
    }
}
