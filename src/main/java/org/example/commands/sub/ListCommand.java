package org.example.commands.sub;

import org.example.commands.ExpenseTracker;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "list", description = "Lists all expenses")
public class ListCommand implements Callable<Integer> {

    @CommandLine.ParentCommand
    private ExpenseTracker parent;

    @Override
    public Integer call() throws Exception {

        System.out.println("Printing expenses...\n");

        String text = parent.getExpenseService().getExpenses();
        System.out.println(text);

        return 0;
    }
}
