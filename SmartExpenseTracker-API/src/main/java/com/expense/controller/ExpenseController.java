package com.expense.controller;

import com.expense.dao.ExpenseDAO;
import com.expense.dto.CategorySummaryResponse;
import com.expense.dto.ExpenseResponse;
import com.expense.model.Expense;
import com.expense.model.MonthlyExpense;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseDAO dao = new ExpenseDAO();

    // ✅ GET ALL EXPENSES
    @GetMapping("/{userId}")
    public List<Expense> getExpenses(@PathVariable int userId) {
        return dao.getExpensesByUser(userId);
    }

    // ✅ ADD EXPENSE (FIXED)
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        dao.addExpense(expense);
        return expense; // 🔥 return same object
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable int id) {
        dao.deleteExpense(id);
        return "Deleted";
    }
    

    // ✅ TOTAL
    @GetMapping("/total/{userId}")
    public double getTotal(@PathVariable int userId) {
        return dao.getTotalExpense(userId);
    }

    // ✅ MONTHLY
    @GetMapping("/monthly/{userId}")
    public double getMonthly(@PathVariable int userId) {
        return dao.getMonthlyExpense(userId);
    }

    // ✅ CATEGORY SUMMARY (FIXED)
  @GetMapping("/category-summary/{userId}")
public List<CategorySummaryResponse> getCategorySummary(@PathVariable int userId) {
    return dao.getCategorySummary(userId);
}

    // ✅ RECENT EXPENSES (FIXED)
    @GetMapping("/recent/{userId}")
public List<ExpenseResponse> getRecent(@PathVariable int userId) {
    return dao.getRecentExpenses(userId);
}

@GetMapping("/monthly-summary/{userId}")
public List<MonthlyExpense> getMonthlySummary(
        @PathVariable int userId) {

    return dao.getMonthlySummary(userId);
}
@PutMapping
public String updateExpense(@RequestBody Expense expense) {

    System.out.println("UPDATE ID = " + expense.getId());
    System.out.println("AMOUNT = " + expense.getAmount());

    dao.updateExpense(expense);

    return "UPDATED";
}

}