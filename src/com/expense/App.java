package com.expense;

import com.expense.dao.ExpenseDAO;
import com.expense.model.Expense;

public class App {

    public static void main(String[] args) {

        Expense expense = new Expense(0, "Food", 250, "2026-03-12");

        ExpenseDAO dao = new ExpenseDAO();

        dao.addExpense(expense);

    }
}