package org.example.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.Expense;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExpenseStorage {
    private static final String FILE_PATH = "expenses.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Type EXPENSE_LIST_TYPE = new TypeToken<List<Expense>>(){}.getType();


    public void isFileExist() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            initializeFile(file);
        }
    }

    public void initializeFile(File file) throws IOException {
        file.createNewFile();
        try (Writer writer = new FileWriter(file)) {
            writer.write("[]");
        }
    }

    public List<Expense> loadFile() {
        try {
            isFileExist();
            try (Reader reader = new FileReader(FILE_PATH)) {
                List<Expense> expenses = gson.fromJson(reader, EXPENSE_LIST_TYPE);
                return expenses != null ? expenses : new ArrayList<>();
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveFile(List<Expense> expenses) {
        try {
            isFileExist();
            try (Writer writer = new FileWriter(FILE_PATH)) {
                gson.toJson(expenses, writer);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
