package com.maingame.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartScreenController {

    private Stage mainStage;
    private static final Logger LOGGER = Logger.getLogger(StartScreenController.class.getName());

    @FXML
    private void handlePlayAction() {

        // Initiate the game music as soon as the game starts.
        MediaController.playButtonClick();
        MediaController.playBackgroundMusic();

        Task<Scene> loadSceneTask = new Task<>() {
            @Override
            protected Scene call() throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                StackPane gamePane = loader.load();
                return new Scene(gamePane, 800, 600);
            }
        };

        loadSceneTask.setOnSucceeded(_ -> Platform.runLater(() -> {
            if (mainStage != null) {
                mainStage.setScene(loadSceneTask.getValue());
                mainStage.show();
            }
        }));

        loadSceneTask.setOnFailed(_ -> {
            Throwable exception = loadSceneTask.getException();
            LOGGER.log(Level.SEVERE, "Failed to load main controller from FXML", exception);
        });

        Thread thread = new Thread(loadSceneTask);
        thread.setDaemon(true);
        thread.start();
    }

    /** Future Expansion: this will be added in the database update.*/
    @FXML
    private void handleLoadGameAction() {
        MediaController.playButtonClick();
    }

    @FXML
    private void handleExitAction() {
        MediaController.playButtonClick();
        if (this.mainStage != null) {
            this.mainStage.close();
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
