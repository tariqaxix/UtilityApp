package com.example.UtilityBill;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UtilityBillApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UtilityBill.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Utility App");
        stage.setScene(scene);
        stage.show();
    }
}

