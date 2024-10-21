package com.example.autoschoolproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField; // Поле для ввода имени пользователя

    @FXML
    private PasswordField passwordField; // Поле для ввода пароля

    @FXML
    private Button setCancelButton; // Кнопка "Отмена"

    @FXML
    private Button setOkButton; // Кнопка "Ок"

    @FXML
    private ImageView imageView; // Компонент для отображения изображения

    @FXML
    public void initialize() {
        // Загружаем изображение при инициализации контроллера
        Image image = new Image(getClass().getResourceAsStream("/authorization.png"));
        imageView.setImage(image);
    }

    @FXML
    public void setCancelButton() {
        // Закрытие текущего окна при нажатии на кнопку "Отмена"
        Stage stage = (Stage) setCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void setOkButton() {
        // Обработка нажатия кнопки "Ок" (логика авторизации)
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authorizationVerification(username, password)) {
            // Открыть следующее окно или выполнить другое действие
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/autoschoolproject/Authorization/students.fxml"));
                AnchorPane root = fxmlLoader.load();


                Stage stage = (Stage) setOkButton.getScene().getWindow();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка авторизации");
            alert.setHeaderText("Неверный логин или пароль !");

            alert.showAndWait();
        }
    }

    private boolean authorizationVerification(String username, String password) {
        // Проверка логина и пароля
        return "admin".equals(username) && "111".equals(password);
    }
}
