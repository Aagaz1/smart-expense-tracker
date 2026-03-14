package com.expense.ui;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    JButton addExpenseButton;
    JButton viewExpenseButton;
    JButton reportButton;
    JButton logoutButton;

    public Dashboard() {

        setTitle("Smart Expense Tracker - Dashboard");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4,1,10,10));

        addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(e -> new AddExpenseForm());
        viewExpenseButton = new JButton("View Expenses");
        reportButton = new JButton("Monthly Report");
        logoutButton = new JButton("Logout");

        add(addExpenseButton);
        add(viewExpenseButton);
        add(reportButton);
        add(logoutButton);

        setVisible(true);
    }
}