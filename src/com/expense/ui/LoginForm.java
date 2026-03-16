package com.expense.ui;

import com.expense.dao.UserDAO;
import com.expense.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    JTextField emailField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginForm() {

        setTitle("Smart Expense Tracker - Login");

        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(3,2,10,10));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");

        loginButton.addActionListener(e -> loginUser());

        add(emailLabel);
        add(emailField);

        add(passwordLabel);
        add(passwordField);

        add(new JLabel(""));
        add(loginButton);

        setVisible(true);
    }

    private void loginUser() {

        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        UserDAO dao = new UserDAO();

        User user = dao.login(email, password);

        if(user != null) {

            JOptionPane.showMessageDialog(this,"Welcome " + user.getName());

            new Dashboard(user);

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,"Invalid Email or Password");

        }

    }

}