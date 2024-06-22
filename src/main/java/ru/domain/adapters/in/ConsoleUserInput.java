package ru.domain.adapters.in;

import ru.domain.adapters.out.ConsoleOutput;
import ru.domain.usecases.UserManager;

/**
 * Обрабатываем консольный ввод для пользовательских операций
 */
public class ConsoleUserInput {
    private ConsoleInput input;
    private ConsoleOutput output;
    private UserManager userManager;

    public ConsoleUserInput(ConsoleInput input, ConsoleOutput output, UserManager userManager) {
        this.input = input;
        this.output = output;
        this.userManager = userManager;
    }

    public void handleRegisterUser() {
        output.println("Enter IO:");
        String userId = input.readLine();
        output.println("Enter Password:");
        String userPassword = input.readLine();

        try {
            userManager.registerUser(userId, userPassword);
            output.println("User registered successfully: ");
        } catch (IllegalArgumentException e) {
            output.println("Error: " + e.getMessage());
        }
    }

    public void handleLoginUser() {
        output.println("Enter IO:");
        String id = input.readLine();
        output.println("Enter Password:");
        String password = input.readLine();

        boolean loggedIn = userManager.loginUser(id, password);
        if (loggedIn) {
            output.println("Logged in successfully");
        } else {
            output.println("Login failed");
        }
    }
}
