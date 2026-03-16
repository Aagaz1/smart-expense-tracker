package com.expense.ui;
import javax.swing.*;
import java.awt.*;
import com.expense.model.User;
import javax.swing.table.DefaultTableModel;
import com.expense.dao.ExpenseDAO;
import com.expense.model.Expense;
import java.util.List;
public class Dashboard extends JFrame {

    private User currentUser;

    JButton addExpenseButton;
    JButton viewExpenseButton;
    JButton reportButton;
    JButton logoutButton;

    JTable expenseTable;
    DefaultTableModel tableModel;

   public Dashboard(User user) {

    this.currentUser = user;

   

        setTitle("Smart Expense Tracker - Dashboard");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(e -> new AddExpenseForm(currentUser));
        viewExpenseButton = new JButton("View Expenses");
        viewExpenseButton.addActionListener(e -> loadExpenses());
        reportButton = new JButton("Monthly Report");
        logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel(new GridLayout(1,4,10,10));

        buttonPanel.add(addExpenseButton);
        buttonPanel.add(viewExpenseButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.NORTH);


        String[] columns = {"Date","Category ID","Amount","Description"};
        tableModel = new DefaultTableModel(columns,0);
        expenseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(expenseTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
    private void loadExpenses() {

    try {

        ExpenseDAO dao = new ExpenseDAO();

        List<Object[]> expenses =
                dao.getExpensesByUser(currentUser.getId());

        tableModel.setRowCount(0);

        for(Object[] row : expenses) {

            tableModel.addRow(row);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

}
}