package dev.antozy.questapp.controllers;

import dev.antozy.questapp.entities.User;
import dev.antozy.questapp.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("in getAllUsers method.");
        return userService.findAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        log.info("in createUser method.");
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        log.info("in getUserById method.");
        //Custom exception
        return userService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User updatedUser) {
        log.info("in updateUserById method.");
        return userService.updateUserById(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        log.info("in deleteUserById method.");
        userService.deleteUserById(userId);
    }
}