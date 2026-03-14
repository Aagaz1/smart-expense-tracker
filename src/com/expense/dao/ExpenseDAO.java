package com.expense.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.expense.database.DatabaseConnection;
import com.expense.model.Expense;

public class ExpenseDAO {

    public void addExpense(Expense expense) {

        String query = "INSERT INTO expenses (user_id, category_id, amount, description, date) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, expense.getUserId());
            stmt.setInt(2, expense.getCategoryId());
            stmt.setDouble(3, expense.getAmount());
            stmt.setString(4, expense.getDescription());
            stmt.setDate(5, new java.sql.Date(expense.getDate().getTime()));

            stmt.executeUpdate();

            System.out.println("Expense added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}