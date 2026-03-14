package com.expense.ui;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginScreen() {

        setTitle("Smart Expense Tracker - Login");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3,2,10,10));

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");

        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel(""));
        add(loginButton);

        // BUTTON CLICK EVENT
        loginButton.addActionListener(e -> loginUser());

        setVisible(true);
    }

    // LOGIN METHOD
    private void loginUser() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if(username.equals("admin") && password.equals("admin")) {

            JOptionPane.showMessageDialog(this,"Login Successful");
            new Dashboard();   // open dashboard
            dispose();         // close login screen

        } else {

            JOptionPane.showMessageDialog(this,"Invalid Credentials");

        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}