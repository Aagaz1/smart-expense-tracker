package com.expense;

import com.expense.dao.ExpenseDAO;
import com.expense.model.Expense;
import java.util.Date;

public class App {

    public static void main(String[] args) {

        Expense expense = new Expense();

        expense.setUserId(1);
        expense.setCategoryId(1);
        expense.setAmount(250);
        expense.setDescription("Food");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        expense.setDate(sqlDate);

        ExpenseDAO dao = new ExpenseDAO();
        dao.addExpense(expense);

    }
}