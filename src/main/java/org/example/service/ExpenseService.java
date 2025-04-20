package org.example.service;

import org.example.model.Expense;
import org.example.storage.ExpenseStorage;
import org.example.storage.MonthHelper;

import java.time.LocalDate;
import java.util.List;

public class ExpenseService {

    ExpenseStorage expenseStorage = new ExpenseStorage();

    public void addExpense(Expense expense) {

        List<Expense> expenses = expenseStorage.loadFile();
        int newId = generateNewId(expenses);
        String date = LocalDate.now().toString();

        expense.setId(newId);
        expense.setDate(date);

        expenses.add(expense);
        expenseStorage.saveFile(expenses);
    }

    public void deleteExpense(int id) {

        List<Expense> expenses = expenseStorage.loadFile();

        if (expenses.stream().noneMatch(expense -> expense.getId() == id)){
            System.out.println("There is no expense with this id.");
            return;
        }
        expenses.removeIf(expense -> expense.getId() == id);
        expenseStorage.saveFile(expenses);

        System.out.println("Expense deleted");
    }

    public String getExpenses() {
        List<Expense> expenses = expenseStorage.loadFile();

        if (expenses.isEmpty()) {
            return "No expenses found";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("# %-3s %-10s  %-22s %s", "ID", "Date", "Description", "Amount\n"));
        for (Expense expense : expenses) {
            int id = expense.getId();
            String idString = String.valueOf(id);
            builder.append(String.format("# %-3s %-10s  %-22s %s", idString, expense.getDate(), expense.getDescription(), expense.getAmount() + "\n"));
        }

        return builder.toString();
    }

    public String getMonthName(int monthNumber) {

        double sum = getExpensesCountForMonth(monthNumber);
        String monthName = MonthHelper.getMonthName(monthNumber);

        return "Expenses for " + monthName + ": " + sum + "₸";
    }

    public double getExpensesCountForMonth(int monthNumber) {

        List<Expense> expenses = expenseStorage.loadFile();
        double sum = 0;

        for (Expense expense : expenses) {

            String date = expense.getDate();
            date = date.substring(5, 7);
            int dateNumber = Integer.parseInt(date);

            if (dateNumber == monthNumber) {
                double amount = expense.getAmount();
                sum += amount;
            }
        }

        return sum;
    }


    private int generateNewId(List<Expense> tasks) {
        return tasks.stream()
                .mapToInt(Expense::getId)
                .max()
                .orElse(0) + 1;
    }
}
