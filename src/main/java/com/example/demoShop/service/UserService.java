package com.example.demoShop.service;

import com.example.demoShop.model.User;
import com.example.demoShop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Знаходить користувача за іменем
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Реєструє нового користувача
    public void registerUser(User user) {
        userRepository.save(user);
    }

    // Метод для автентифікації користувача за іменем користувача та паролем
    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        // Перевіряємо, чи користувач існує та чи збігається пароль
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}