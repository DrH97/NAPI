package com.ics.project.services;

import com.ics.project.controllers.exceptions.UserExistsException;
import com.ics.project.controllers.exceptions.UserNotFoundException;
import com.ics.project.models.User;
import com.ics.project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User user) throws UserExistsException {
        User existingUser = userRepo.findByIdNumber(user.getIdNumber().trim());

        if (existingUser != null) {
            throw UserExistsException.createWith(user);
        }

        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User me(Long id) throws UserNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> UserNotFoundException.createWith(id));
    }

    @Override
    public User byIdNumber(String idNumber) throws UserNotFoundException {
        return userRepo.findByIdNumber(idNumber);
    }
}
