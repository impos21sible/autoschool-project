package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Замените на ваши данные
    private static final String URL = "jdbc:postgresql://localhost:5433/postgres"; // Имя вашей базы данных
    private static final String USER = "postgres"; // Имя пользователя
    private static final String PASSWORD = "111"; // Пароль

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
