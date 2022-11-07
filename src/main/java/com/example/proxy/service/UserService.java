package com.example.proxy.service;

import com.example.proxy.entity.User;
import com.example.proxy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService  {

    private UserRepository userRepository;

    public User register(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    public void updatePassword(String email, String password) {
        User user = userRepository.findByUsername(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        userRepository.save(user);
    }

    public User getByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public Page<User> getAll(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }

}
