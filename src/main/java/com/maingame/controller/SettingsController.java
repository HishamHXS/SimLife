package com.maingame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class SettingsController {

    @FXML
    private VBox settingsContainer;
    @FXML
    private Button musicStateButton;

    @FXML
    private void handleCloseAction() {
        MediaController.playButtonClick();

        if (settingsContainer.getParent() instanceof VBox parentContainer) {
            parentContainer.setVisible(false);
        }
    }

    @FXML
    private void changeMusicStateAction() {
        if (Objects.equals(musicStateButton.getText(), "Pause")) {
            MediaController.stopBackgroundMusic();
            musicStateButton.setText("Play");
        } else {
            MediaController.playBackgroundMusic();
            musicStateButton.setText("Pause");
        }
    }

    @FXML
    private void skipSongAction() {
        MediaController.skipTrack();
    }
}
