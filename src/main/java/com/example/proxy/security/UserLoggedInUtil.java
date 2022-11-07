package com.example.proxy.security;

import com.example.proxy.entity.User;
import com.example.proxy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserLoggedInUtil {

    private UserService userService;

    public User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null)
            return null;
        User user = userService.getByUsername(username);
        return user;
    }

}