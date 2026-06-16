package com.expense.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.expense.database.DatabaseConnection;
import com.expense.model.Budget;

public class BudgetDAO {

   public void saveBudget(Budget budget) {

    try {

        Connection conn =
            DatabaseConnection.getConnection();

        String checkSql =
            "SELECT id FROM budgets WHERE user_id=?";

        PreparedStatement checkStmt =
            conn.prepareStatement(checkSql);

        checkStmt.setInt(1, budget.getUserId());

        ResultSet rs =
            checkStmt.executeQuery();

        if (rs.next()) {

            String updateSql =
                "UPDATE budgets SET monthly_budget=? WHERE user_id=?";

            PreparedStatement updateStmt =
                conn.prepareStatement(updateSql);

            updateStmt.setDouble(
                1,
                budget.getMonthlyBudget()
            );

            updateStmt.setInt(
                2,
                budget.getUserId()
            );

            updateStmt.executeUpdate();

        } else {

            String insertSql =
                "INSERT INTO budgets(user_id, monthly_budget) VALUES(?, ?)";

            PreparedStatement insertStmt =
                conn.prepareStatement(insertSql);

            insertStmt.setInt(
                1,
                budget.getUserId()
            );

            insertStmt.setDouble(
                2,
                budget.getMonthlyBudget()
            );

            insertStmt.executeUpdate();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public Budget getBudget(int userId) {

    Budget budget = null;

    try {

        Connection conn =
            DatabaseConnection.getConnection();

        String sql =
            "SELECT * FROM budgets WHERE user_id=?";

        PreparedStatement stmt =
            conn.prepareStatement(sql);

        stmt.setInt(1, userId);

        ResultSet rs =
            stmt.executeQuery();

        if (rs.next()) {

            budget = new Budget();

            budget.setId(rs.getInt("id"));
            budget.setUserId(rs.getInt("user_id"));
            budget.setMonthlyBudget(
                rs.getDouble("monthly_budget")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return budget;
}
}