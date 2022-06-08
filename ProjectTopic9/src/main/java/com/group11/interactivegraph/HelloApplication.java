package com.group11.interactivegraph;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application{
    Button button1;

    @Override
    public void start(Stage stage) throws IOException {
        button1 = new Button("Click me");

        button1.setOnAction(actionEvent -> {
            System.out.println("OK!");
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button1);

        Scene scene = new Scene(layout, 320, 240);
        stage.setTitle("Cái này chỉ test thôi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}