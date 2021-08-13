package com.zemaitiz.gihub.mathgame.gg.service;

import com.zemaitiz.gihub.mathgame.gg.model.User;
import com.zemaitiz.gihub.mathgame.gg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }
}
