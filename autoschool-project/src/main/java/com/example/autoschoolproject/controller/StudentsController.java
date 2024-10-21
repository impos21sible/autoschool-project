package com.example.autoschoolproject.controller;
import DataBaseConnection.DatabaseConnection;

import com.example.autoschoolproject.classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.io.IOException;
import java.time.LocalDate;

public class StudentsController {

    @FXML
    private Button setStudentsButton; // Кнопка "Ученики"

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> firstNameColumn; // Изменено на camelCase
    @FXML
    private TableColumn<User, String> lastNameColumn; // Изменено на camelCase
    @FXML
    private TableColumn<User, LocalDate> dateOfBirthColumn; // Изменено на camelCase
    @FXML
    private TableColumn<User, String> phoneNumberColumn; // Изменено на camelCase
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, LocalDate> registrationDateColumn; // Изменено на camelCase

    @FXML
    public void setStudentsButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/autoschoolproject/Authorization/students.fxml"));
        AnchorPane students = fxmlLoader.load();
        Stage stage = (Stage) setStudentsButton.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(students);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);  // Добавлено для отображения новой сцены
    }

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        registrationDateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        // Загружаем данные из базы данных
        loadStudentsData();
    }

    private void loadStudentsData() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getDate("registration_date").toLocalDate()
                );
                userTable.getItems().add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Здесь можно обработать ошибку, например, вывести сообщение пользователю
        }
    }
}

