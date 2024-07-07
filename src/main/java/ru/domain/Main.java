package ru.domain;

import ru.domain.dao.BookingDAO;
import ru.domain.dao.ConferenceRoomDAO;
import ru.domain.dao.UserDAO;
import ru.domain.dao.WorkspaceDAO;
import ru.domain.dao.impl.BookingDAOImpl;
import ru.domain.dao.impl.ConferenceRoomDAOImpl;
import ru.domain.dao.impl.UserDAOImpl;
import ru.domain.dao.impl.WorkspaceDAOImpl;
import ru.domain.handlers.WorkspaceHandler;
import ru.domain.io.in.ConsoleUserInput;
import ru.domain.io.in.UserInput;
import ru.domain.io.out.ConsoleUserOutput;
import ru.domain.io.out.UserOutput;
import ru.domain.managers.ConferenceRoomManager;
import ru.domain.managers.UserAuthenticationManager;
import ru.domain.managers.UserRegistrationManager;
import ru.domain.managers.WorkspaceManager;
import ru.domain.menu.MenuCommandExecutor;
import ru.domain.handlers.UserHandler;
import ru.domain.handlers.ConferenceRoomHandler;
import ru.domain.menu.MenuDisplay;
import ru.domain.menu.MenuHandler;

/**
 * Main класс для запуска приложения
 */
public class Main {
    public static void main(String[] args) {
        UserInput consoleUserInput = new ConsoleUserInput(); // Объект для ввода данных с консоли
        UserOutput consoleUserOutput = new ConsoleUserOutput(); // Объект для вывода данных в консоль

        UserDAO userDAO = new UserDAOImpl();
        BookingDAO bookingDAO = new BookingDAOImpl();
        WorkspaceDAO workspaceDAO = new WorkspaceDAOImpl();
        ConferenceRoomDAO conferenceRoomDAO = new ConferenceRoomDAOImpl();

        UserRegistrationManager registrationManager = new UserRegistrationManager(userDAO); // Менеджер для регистрации пользователей
        UserAuthenticationManager userAuthenticationManager = new UserAuthenticationManager(userDAO); // Менеджер для аутентификации пользователей
        ConferenceRoomManager conferenceRoomManager = new ConferenceRoomManager(conferenceRoomDAO, workspaceDAO); // Менеджер для управления конференц-залами
        WorkspaceManager workspaceManager = new WorkspaceManager(workspaceDAO, bookingDAO); // Менеджер для управления рабочими местами

        UserHandler userHandle = new UserHandler(consoleUserInput, consoleUserOutput, registrationManager, userAuthenticationManager); // Сервис для обработки операций с пользователями
        ConferenceRoomHandler conferenceRoomHandle = new ConferenceRoomHandler(consoleUserInput, consoleUserOutput, conferenceRoomManager, workspaceManager); // Сервис для обработки операций с конференц-залами
        WorkspaceHandler workspaceHandle = new WorkspaceHandler(consoleUserInput, consoleUserOutput, workspaceManager, conferenceRoomManager); // Сервис для обработки операций с рабочими местами

        MenuCommandExecutor commandExecutor = new MenuCommandExecutor(userHandle, conferenceRoomHandle, workspaceHandle); // Исполнитель команд меню
        MenuDisplay menuDisplay = new MenuDisplay(consoleUserOutput); // Отображение меню
        MenuHandler menuHandler = new MenuHandler(consoleUserInput, commandExecutor, menuDisplay); // Обработчик ввода пользователя в меню

        try {
            menuHandler.showMainMenu(); // Запуск меню приложения
        } finally {
            consoleUserInput.close(); // Закрываем поток Scanner
        }
    }
}
