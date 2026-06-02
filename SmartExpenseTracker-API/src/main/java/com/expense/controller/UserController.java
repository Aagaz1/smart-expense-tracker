package com.expense.controller;

import org.springframework.web.bind.annotation.*;

import com.expense.dao.UserDAO;
import com.expense.dto.LoginResponse;
import com.expense.model.User;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserDAO dao = new UserDAO();

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        dao.register(user);
        return "User Registered";
    }

    // ✅ LOGIN
    @PostMapping("/login")
public LoginResponse login(@RequestBody User user) {

    User u = dao.login(user.getEmail(), user.getPassword());

    if (u != null) {
        return new LoginResponse(
            u.getId(),
            u.getName(),
            u.getEmail()
        );
    }

    return null;
}
}