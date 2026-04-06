package com.expense.controller;

import com.expense.dao.ExpenseDAO;
import com.expense.dto.ExpenseResponse;
import com.expense.model.Expense;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseDAO dao = new ExpenseDAO();

    // ✅ GET all expenses for user
  @GetMapping("/{userId}")
public List<Expense> getExpenses(@PathVariable int userId){
    return dao.getExpensesByUser(userId);
}

    // ✅ ADD expense
  @PostMapping
public ExpenseResponse addExpense(@RequestBody Expense expense) {
    dao.addExpense(expense);

    return new ExpenseResponse(
        expense.getId(),
        "Food", // temporary fix (or fetch from DB later)
        expense.getAmount(),
        expense.getDescription(),
        expense.getDate()
    );
}

    // ✅ DELETE expense
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable int id) {
        dao.deleteExpense(id);
        return "Deleted";
    }
    // TOTAL EXPENSE
@GetMapping("/total/{userId}")
public double getTotal(@PathVariable int userId) {
    return dao.getTotalExpense(userId);
}

// MONTHLY EXPENSE
@GetMapping("/monthly/{userId}")
public double getMonthly(@PathVariable int userId) {
    return dao.getMonthlyExpense(userId);
}

// CATEGORY SUMMARY
@GetMapping("/category-summary/{userId}")
public List<Object[]> getCategorySummary(@PathVariable int userId) {
    return dao.getCategorySummary(userId);
}

// RECENT EXPENSES
@GetMapping("/recent/{userId}")
public List<ExpenseResponse> getRecent(@PathVariable int userId) {
    return dao.getRecentExpenses(userId);
}
}