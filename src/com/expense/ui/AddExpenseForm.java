package com.expense.ui;
import com.expense.dao.ExpenseDAO;
import com.expense.dao.CategoryDAO;
import com.expense.model.Expense;
import com.expense.model.Category;
import com.expense.model.User;


import java.util.Date;
// import java.util.List;

import javax.swing.*;
import java.awt.*;

public class AddExpenseForm extends JFrame {

    private User currentUser;

    JTextField amountField;
    JComboBox<String> categoryDropdown;
    JTextField descriptionField;
    JTextField dateField;

    JButton saveButton;

    public AddExpenseForm(User user) {

        this.currentUser = user;

        setTitle("Add Expense");
        setSize(400,300);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5,2,10,10));

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        categoryDropdown = new JComboBox<>();
        loadCategories();

        JButton addCategoryButton = new JButton("+");
        addCategoryButton.addActionListener(e -> addNewCategory());

        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.add(categoryDropdown);
        categoryPanel.add(addCategoryButton);

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
        add(categoryPanel);

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
        String selected = (String) categoryDropdown.getSelectedItem();
        int categoryId = Integer.parseInt(selected.split(" - ")[0]);
        String description = descriptionField.getText();

        String dateText = dateField.getText();
        Date date = java.sql.Date.valueOf(dateText);

        Expense expense = new Expense();

        expense.setUserId(currentUser.getId());
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

    try {

        CategoryDAO dao = new CategoryDAO();

        java.util.List<Category> categories = dao.getAllCategories();

        for(Category c : categories) {

            categoryDropdown.addItem(c.getId() + " - " + c.getName());
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

}
private void addNewCategory() {

    String name = JOptionPane.showInputDialog(this,"Enter Category Name");

    if(name != null && !name.trim().isEmpty()) {

        try {

            CategoryDAO dao = new CategoryDAO();
            dao.addCategory(name);

            categoryDropdown.removeAllItems();
            loadCategories();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
}
