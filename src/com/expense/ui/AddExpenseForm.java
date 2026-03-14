package com.expense.ui;

import com.expense.dao.ExpenseDAO;
import com.expense.dao.CategoryDAO;
import com.expense.model.Expense;
import com.expense.model.Category;

import java.util.Date;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class AddExpenseForm extends JFrame {

    JTextField amountField;
    JComboBox<String> categoryBox;
    JTextField descriptionField;
    JTextField dateField;

    JButton saveButton;

    public AddExpenseForm() {

        setTitle("Add Expense");
        setSize(400,300);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5,2,10,10));

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        categoryBox = new JComboBox<>();

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateField = new JTextField();

        saveButton = new JButton("Save Expense");

        // Button Click Event
        saveButton.addActionListener(e -> saveExpense());

        add(amountLabel);
        add(amountField);

        add(categoryLabel);
        add(categoryBox);

        add(descriptionLabel);
        add(descriptionField);

        add(dateLabel);
        add(dateField);

        add(new JLabel(""));
        add(saveButton);
        loadCategories();
        setVisible(true);
    }

    

    // METHOD MUST BE OUTSIDE CONSTRUCTOR
  private void saveExpense() {

    try {

        double amount = Double.parseDouble(amountField.getText());
        int categoryId = categoryBox.getSelectedIndex() + 1;
        String description = descriptionField.getText();

        String dateText = dateField.getText();
        Date date = java.sql.Date.valueOf(dateText);

        Expense expense = new Expense();

        expense.setUserId(1);
        expense.setCategoryId(categoryId);
        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setDate(date);

        ExpenseDAO dao = new ExpenseDAO();
        dao.addExpense(expense);

        JOptionPane.showMessageDialog(this,"Expense Saved Successfully");

    } catch(Exception e) {

    e.printStackTrace();

    JOptionPane.showMessageDialog(this,
        "Error Saving Expense: " + e.getMessage());
}

}
private void loadCategories() {

    CategoryDAO dao = new CategoryDAO();

    List<Category> categories = dao.getAllCategories();

    for(Category c : categories) {

        categoryBox.addItem(c.getName());

    }
}
}
