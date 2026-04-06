package com.expense.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;  
import com.expense.database.DatabaseConnection;
import com.expense.dto.ExpenseResponse;
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
    public List<Expense> getExpensesByUser(int userId) {

    List<Expense> expenses = new ArrayList<>();

    try {
        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT e.id, e.user_id, c.name AS category, e.amount, e.description, e.date "
                   + "FROM expenses e "
                   + "JOIN categories c ON e.category_id = c.id "
                   + "WHERE e.user_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Expense exp = new Expense();

            exp.setId(rs.getInt("id"));
            exp.setUserId(rs.getInt("user_id"));
            exp.setCategoryId(0); // optional
            exp.setAmount(rs.getDouble("amount"));
            exp.setDescription(rs.getString("description"));
            exp.setDate(rs.getDate("date"));

            // 👇 ADD THIS FIELD (important)
            exp.setCategory(rs.getString("category"));

            expenses.add(exp);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return expenses;
}
public List<Object[]> getAllExpenses() {

    List<Object[]> list = new ArrayList<>();

    try {
        Connection conn = DatabaseConnection.getConnection();

        String sql = 
            "SELECT e.id, e.user_id, c.name AS category, e.amount, e.description, e.date " +
            "FROM expenses e " +
            "JOIN categories c ON e.category_id = c.id";

        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Object[] row = {
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("category"),
                rs.getDouble("amount"),
                rs.getString("description"),
                rs.getDate("date")
            };

            list.add(row);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<ExpenseResponse> getRecentExpenses(int userId) {

    List<ExpenseResponse> list = new ArrayList<>();

    try {
        Connection conn = DatabaseConnection.getConnection();

        String sql =
            "SELECT e.id, c.name AS category, e.amount, e.description, e.date " +
            "FROM expenses e " +
            "JOIN categories c ON e.category_id = c.id " +
            "WHERE e.user_id = ? " +
            "ORDER BY e.date DESC LIMIT 10";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(new ExpenseResponse(
                rs.getInt("id"),
                rs.getString("category"),
                rs.getDouble("amount"),
                rs.getString("description"),
                rs.getDate("date")
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
public double getTotalExpense(int userId) {

    double total = 0;

    try {

        Connection conn = DatabaseConnection.getConnection();

        String sql = "SELECT SUM(amount) AS total FROM expenses WHERE user_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            total = rs.getDouble("total");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return total;
}

public double getMonthlyExpense(int userId) {

    double total = 0;

    try {

        Connection conn = DatabaseConnection.getConnection();

        String sql =
        "SELECT SUM(amount) AS total FROM expenses " +
        "WHERE user_id = ? AND MONTH(date) = MONTH(CURRENT_DATE())";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, userId);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            total = rs.getDouble("total");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return total;
}
public List<Object[]> getCategorySummary(int userId) {

    List<Object[]> list = new ArrayList<>();

    try {

        Connection conn = DatabaseConnection.getConnection();

        String sql =
            "SELECT c.name, SUM(e.amount) AS total " +
            "FROM expenses e " +
            "JOIN categories c ON e.category_id = c.id " +
            "WHERE e.user_id = ? " +
            "GROUP BY c.name";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Object[] row = {
                rs.getString("name"),
                rs.getDouble("total")
            };

            list.add(row);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
public void deleteExpense(int expenseId) {

    try {
        Connection conn = DatabaseConnection.getConnection();

        String sql = "DELETE FROM expenses WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, expenseId);

        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}