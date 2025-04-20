package org.example.commands;

import org.example.commands.sub.AddCommand;
import org.example.commands.sub.DeleteCommand;
import org.example.commands.sub.ListCommand;
import org.example.commands.sub.SummaryCommand;
import org.example.service.ExpenseService;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "expense-tracker", mixinStandardHelpOptions = true, version = "1.0",
        description = "Expense Tracker", subcommands = {AddCommand.class, DeleteCommand.class, ListCommand.class, SummaryCommand.class})
public class ExpenseTracker implements Callable<Integer> {

    private final ExpenseService expenseService = new ExpenseService();

    public ExpenseService getExpenseService() {
        return expenseService;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ExpenseTracker()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello World");
        return 0;
    }

}
