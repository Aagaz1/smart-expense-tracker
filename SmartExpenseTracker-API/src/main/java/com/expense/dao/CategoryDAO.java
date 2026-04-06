package com.expense.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.expense.database.DatabaseConnection;
import com.expense.model.Category;

public class CategoryDAO {

    // Method to fetch all categories from database
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        try {

            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM categories";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );

                categories.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }


    // Method to add new category
    public void addCategory(String name) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String sql = "INSERT INTO categories(name) VALUES(?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}