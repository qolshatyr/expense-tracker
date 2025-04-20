package org.example.commands.sub;

import org.example.commands.ExpenseTracker;
import org.example.model.Expense;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "add", description = "Adds a new expense")
public class AddCommand implements Callable<Integer> {

    @CommandLine.ParentCommand
    private ExpenseTracker parent;

    @CommandLine.Option(names = {"-d", "--description"}, description = "The expense description", required = true)
    String description;

    @CommandLine.Option(names = {"-a", "--amount"}, description = "The expense amount", required = true)
    double amount;

    @Override
    public Integer call() throws Exception {
        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);

        parent.getExpenseService().addExpense(expense);
        System.out.println("Expense added");

        return 0;
    }
}
