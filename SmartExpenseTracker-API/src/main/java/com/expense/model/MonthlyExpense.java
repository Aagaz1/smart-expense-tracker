package com.expense.model;

public class MonthlyExpense {

    private String month;
    private double total;

    public MonthlyExpense() {
    }

    public MonthlyExpense(String month, double total) {
        this.month = month;
        this.total = total;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}