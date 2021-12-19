package com.geekbrains.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class  AppStarter extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("AppWindow.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();


    }
}