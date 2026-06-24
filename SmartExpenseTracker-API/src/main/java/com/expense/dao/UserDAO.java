package com.expense.dao;

import com.expense.database.DatabaseConnection;
import com.expense.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User login(String email, String password) {

        User user = null;

        try {

            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {

                user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    public void register(User user) {

    try {

        Connection conn = DatabaseConnection.getConnection();

        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());

        stmt.executeUpdate();

        System.out.println("User registered successfully!");

    } catch(Exception e) {
        e.printStackTrace();
    }
}
public boolean emailExists(String email) {

    try {

        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM users WHERE email=?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        return rs.next();

    } catch(Exception e) {
        e.printStackTrace();
    }

    return false;
}
}
