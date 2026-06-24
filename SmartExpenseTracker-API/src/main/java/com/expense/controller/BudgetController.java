package com.expense.controller;

import org.springframework.web.bind.annotation.*;

import com.expense.dao.BudgetDAO;
import com.expense.model.Budget;

@CrossOrigin("*")
@RestController
@RequestMapping("/budget")
public class BudgetController {

    private BudgetDAO dao = new BudgetDAO();

    @PostMapping
    public String saveBudget(@RequestBody Budget budget) {

        dao.saveBudget(budget);

        return "BUDGET_SAVED";
    }

   @GetMapping("/{userId}")
public double getBudget(@PathVariable int userId) {

    Budget budget = dao.getBudget(userId);

    return budget != null
            ? budget.getMonthlyBudget()
            : 0;
}
}