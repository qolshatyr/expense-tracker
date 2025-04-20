package org.example.commands.sub;

import org.example.commands.ExpenseTracker;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "delete", description = "Deletes an expense")
public class DeleteCommand implements Callable<Integer> {

    @CommandLine.ParentCommand
    private ExpenseTracker parent;

    @CommandLine.Option(names = {"--id"}, description = "The expense id", required = true)
    int id;

    @Override
    public Integer call() throws Exception {

        parent.getExpenseService().deleteExpense(id);

        return 0;
    }
}
