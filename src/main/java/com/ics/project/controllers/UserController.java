package com.ics.project.controllers;

import com.ics.project.controllers.exceptions.GlobalExceptionHandler;
import com.ics.project.controllers.exceptions.UserNotFoundException;
import com.ics.project.models.User;
import com.ics.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() { return ResponseEntity.ok(userService.getAllUsers()); }

    @GetMapping(value = "{id}")
    public ResponseEntity<User> me(@PathVariable(name = "id") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.me(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

}
