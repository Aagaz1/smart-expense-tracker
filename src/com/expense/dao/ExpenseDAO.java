package com.expense.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.expense.database.DatabaseConnection;
import com.expense.model.Expense;

public class ExpenseDAO {

    public void addExpense(Expense expense) {

        String query = "INSERT INTO expenses (category, amount, date) VALUES (?, ?, ?)";

        try {

            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, expense.getCategory());
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getDate());

            stmt.executeUpdate();

            System.out.println("Expense added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}