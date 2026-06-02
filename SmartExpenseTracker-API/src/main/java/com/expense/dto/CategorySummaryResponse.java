package com.expense.dto;

public class CategorySummaryResponse {

    private String category;
    private double totalSpent;

    public CategorySummaryResponse(String category, double totalSpent) {
        this.category = category;
        this.totalSpent = totalSpent;
    }

    public String getCategory() {
        return category;
    }

    public double getTotalSpent() {
        return totalSpent;
    }
}