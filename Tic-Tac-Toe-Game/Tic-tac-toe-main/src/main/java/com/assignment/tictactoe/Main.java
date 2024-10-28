package com.assignment.tictactoe; // Package declaration for the Tic-Tac-Toe application

import javafx.application.Application; // Importing JavaFX Application class
import javafx.fxml.FXMLLoader; // Importing FXMLLoader for loading FXML files
import javafx.scene.Parent; // Importing Parent class to represent the root node of the scene graph
import javafx.scene.Scene; // Importing Scene class to create a scene
import javafx.stage.Stage; // Importing Stage class for the application window


public class Main extends Application {

    // Main entry point of the application
    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }

    // Override the start method to set up the primary stage
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file for the board view
        Parent load = FXMLLoader.load(getClass().getResource("/view/BoardView.fxml"));

        // Create a new Scene with the loaded parent node
        Scene scene = new Scene(load);

        // Set the scene to the stage
        stage.setScene(scene);

        // Display the stage
        stage.show();
    }
}
