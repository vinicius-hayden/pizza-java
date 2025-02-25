/**********************************************
 Workshop #3
 Course: - Semester 5
 Last Name: Souza da Silva
 First Name: Vinicius
 ID: 135067221
 Section: NDD
 This assignment represents my own work in accordance with Seneca Academic Policy.
 VHSS
 Date:24/02/2025
 **********************************************/

package com.example.pizzaworkshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pizzaworkshop/PizzaOrderView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pizza Order Application");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}