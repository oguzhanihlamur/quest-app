package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User saveOneUser(User newUser);

    User findUserById(Long userId);

    User updateUserById(Long userId, User user);

    void deleteUserById(Long userId);

    User getUserByUserName(String userName);
}
