package com.expense.model;

public class Budget {

    private int id;
    private int userId;
    private double monthlyBudget;

    public Budget() {
    }

    public Budget(int id, int userId, double monthlyBudget) {
        this.id = id;
        this.userId = userId;
        this.monthlyBudget = monthlyBudget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }
}