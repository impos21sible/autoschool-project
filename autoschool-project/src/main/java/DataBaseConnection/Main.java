package DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
                connection.close(); // Закрываем соединение после проверки
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Выводим ошибку, если подключение не удалось
        }
    }
}
