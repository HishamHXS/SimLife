package com.maingame.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeathScreenController {

    private static final Logger LOGGER = Logger.getLogger(DeathScreenController.class.getName());

    @FXML
    private VBox deathScreenContainer;

    @FXML
    private void returnToMainMenu() {
        try {
            Stage stage = (Stage) deathScreenContainer.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startscreen.fxml"));

            Scene mainMenuScene = new Scene(loader.load(),800, 600);

            StartScreenController controller = loader.getController();
            controller.setMainStage(stage);

            stage.setScene(mainMenuScene);
            stage.show();

        } catch (IOException e) {
          LOGGER.log(Level.SEVERE, "Failed to load start screen from FXML", e);
        }
    }
}
