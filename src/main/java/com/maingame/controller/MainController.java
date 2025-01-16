package com.maingame.controller;

import com.maingame.data.RealNameFaker;
import com.maingame.model.decision.Decision;
import com.maingame.model.Player;
import com.maingame.service.CareerService;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.maingame.helper.ActionChance.rollInt;

public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private Player player;
    private DecisionController decisionController;
    private CareerController careerController;
    private RelationshipsController relationshipsController;
    private SettingsController settingsController;
    private DeathScreenController deathScreenController;

    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleIntegerProperty age = new SimpleIntegerProperty(0);
    private final SimpleStringProperty wealth = new SimpleStringProperty();
    private final SimpleStringProperty happiness = new SimpleStringProperty();
    private final SimpleStringProperty health = new SimpleStringProperty();
    private final SimpleStringProperty message = new SimpleStringProperty("Welcome to the world!");

    @FXML
    private VBox decisionPopup;
    @FXML
    private VBox careerPopup;
    @FXML
    private VBox relationshipsPopup;
    @FXML
    private VBox settingsPopup;
    @FXML
    private VBox deathPopup;

    @FXML
    private Text nameText;
    @FXML
    private Text ageText;
    @FXML
    private Text wealthText;
    @FXML
    private Text happinessText;
    @FXML
    private Text healthText;
    @FXML
    private TextArea messageConsole;

    public void initialize() {
        Task<Void> initializeGame = new Task<>() {
            @Override
            protected Void call() {
                player = new Player(RealNameFaker.getFirstName(), RealNameFaker.getLastName(),
                        0, 0, rollInt(40, 100));

                Platform.runLater(() -> {
                    nameText.textProperty().bind(name);
                    name.set(player.getFullName());

                    ageText.textProperty().bind(age.asString());
                    wealthText.textProperty().bind(wealth);
                    happinessText.textProperty().bind(happiness);
                    healthText.textProperty().bind(health);

                    messageConsole.textProperty().bind(message);
                    messageConsole.setEditable(false);
                });

                FXMLLoader decisionLoader = new FXMLLoader(getClass().getResource("/fxml/decision.fxml"));
                Platform.runLater(() -> {
                    try {
                        decisionPopup.getChildren().add(decisionLoader.load());
                        decisionController = decisionLoader.getController();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Failed to load decision controller from FXML", e);
                    }
                });

                FXMLLoader careerLoader = new FXMLLoader(getClass().getResource("/fxml/career.fxml"));
                Platform.runLater(() -> {
                    try {
                        careerPopup.getChildren().add(careerLoader.load());
                        careerController = careerLoader.getController();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Failed to load career controller from FXML", e);
                    }
                });

                FXMLLoader relationshipLoader = new FXMLLoader(getClass().getResource("/fxml/relationships.fxml"));
                Platform.runLater(() -> {
                    try {
                        relationshipsPopup.getChildren().add(relationshipLoader.load());
                        relationshipsController = relationshipLoader.getController();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Failed to load relationship controller from FXML", e);
                    }
                });

                FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/fxml/settings.fxml"));
                Platform.runLater(() -> {
                    try {
                        settingsPopup.getChildren().add(settingsLoader.load());
                        settingsController = settingsLoader.getController();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Failed to load settings controller from FXML", e);
                    }
                });

                FXMLLoader deathLoader = new FXMLLoader(getClass().getResource("/fxml/deathscreen.fxml"));
                Platform.runLater(() -> {
                    try {
                        deathPopup.getChildren().add(deathLoader.load());
                        deathScreenController = deathLoader.getController();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Failed to load death controller from FXML", e);
                    }
                });

                return null;
            }
        };

        new Thread(initializeGame).start();
    }

    @FXML
    private void handleAgeAction() {
        MediaController.playButtonClick();

        this.player.updateAge();
        handleIfPlayerDead();
        addToDecisionLog();
        addToMessageLog();

        age.set(this.player.getAge());
        wealth.set("$" + this.player.getCash());
        happiness.set(this.player.getHappiness() + "%");
        health.set(this.player.getHealth() + "%");
    }

    @FXML
    private void handleCareerAction() {
        MediaController.playButtonClick();

        careerController.setPlayerCareer(new CareerService(), this.player.getPlayerCareer(), this.player);
        careerController.display();
        careerPopup.setVisible(true);
    }

    @FXML
    private void handleRelationshipsAction() {
        MediaController.playButtonClick();

        relationshipsController.listRelationships(this.player);
        relationshipsPopup.setVisible(true);
    }

    @FXML
    private void handleSettingsAction() {
        MediaController.playButtonClick();

        settingsPopup.setVisible(true);
    }

    private void handleIfPlayerDead() {
        if (!this.player.getPlayerAlive()) {
            deathPopup.setVisible(true);
        }
    }

    /** This adds all read only player updates to the message box. */
    private void addToMessageLog(){
        StringBuilder newMessage = new StringBuilder();
        newMessage.append(message.get()).append('\n');

        for (String log: this.player.getPlayerUpdatesLog()) {
            newMessage.append(log).append('\n');
        }

        message.set(newMessage.toString());
        messageConsole.setScrollTop(Double.MAX_VALUE);

        this.player.clearPlayerUpdatesLog();
    }

    /** This adds all player action required decisions to the decisionPopup. */
    private void addToDecisionLog()  {
        if (!this.player.getPlayerDecisionLog().isEmpty()) {
            decisionPopup.setVisible(true);
            for (Decision decision: this.player.getPlayerDecisionLog()) {
                    decisionController.display(decision);
            }
            this.player.clearPlayerDecisionsLog();
        }
    }
}
