package com.expense.dto;

import java.util.Date;

public class ExpenseResponse {

    private int id;
    private String category;
    private double amount;
    private String description;
    private Date date;

    // ✅ Default constructor (REQUIRED)
    public ExpenseResponse() {}

    // ✅ Parameterized constructor (optional)
    public ExpenseResponse(int id, String category, double amount, String description, Date date) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // ✅ Getters
    public int getId() { return id; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public Date getDate() { return date; }

    // ✅ Setters (REQUIRED)
    public void setId(int id) { this.id = id; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(Date date) { this.date = date; }
}