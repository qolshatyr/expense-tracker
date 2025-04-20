package org.example.commands.sub;

import org.example.commands.ExpenseTracker;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "summary", description = "Summarizes all expenses")
public class SummaryCommand implements Callable<Integer> {
    @CommandLine.ParentCommand
    private ExpenseTracker parent;

    @CommandLine.Option(names = {"-m", "--month"}, description = "The date to summarize expenses for", required = true)
    int month;

    @Override
    public Integer call() throws Exception {

        System.out.println("Printing expenses...\n");

        String text = parent.getExpenseService().getMonthName(month);
        System.out.println(text);

        return 0;
    }
}
