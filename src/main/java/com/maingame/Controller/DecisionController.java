package com.maingame.Controller;

import com.maingame.Helper.ActionChance;
import com.maingame.Model.Decision.Decision;
import com.maingame.Model.Decision.PlayerDecision;
import com.maingame.Model.Decision.SocialDecision;
import com.maingame.Model.NonPlayerCharacter;
import com.maingame.Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.maingame.Helper.ActionChance.rollInt;

public class DecisionController {

    private static final Logger LOGGER = Logger.getLogger(DecisionController.class.getName());

    private Decision currentDecision;

    @FXML
    private DialogPane decisionContainer;
    @FXML
    private Text playerScenarioText;

    public void display(Decision decision) {
        currentDecision = decision;
        playerScenarioText.setText(currentDecision.getInteraction());
    }

    @FXML
    private void handleYesAction() {
        MediaController.playButtonClick();

        if (decisionContainer.getParent() instanceof VBox parentStackPane) {
            processYesAction(parentStackPane);
        }
    }

    @FXML
    private void handleNoAction() {
        MediaController.playButtonClick();

        if (decisionContainer.getParent() instanceof VBox parentStackPane) {
            parentStackPane.setVisible(false);
        }
    }

    private void processYesAction(VBox parentStackPane) {
        switch (currentDecision) {
            case SocialDecision socialDecision -> handleSocialDecision(socialDecision);
            case PlayerDecision playerDecision -> handlePlayerDecision(playerDecision);
            default -> {
                LOGGER.log(Level.WARNING, "Unexpected decision type: " + currentDecision.getClass().getName());
                throw new IllegalArgumentException("Unexpected decision type: " + currentDecision.getClass().getName());
            }
        }

        parentStackPane.setVisible(false);
    }

    private void handleSocialDecision(SocialDecision socialDecision) {
        String interactionType = socialDecision.getInteractionType();
        Player player = socialDecision.getPlayer();
        NonPlayerCharacter nonPlayerCharacter = socialDecision.getNonPlayerCharacter();

        switch (interactionType) {
            case "Friend Request" -> {
                player.getPlayerFriends().addRelation(nonPlayerCharacter);
                nonPlayerCharacter.updateRelationshipLevel();
            }
            case "Positive" -> player.updateHappiness(5);
            case "Negative" -> player.updateHappiness(-5);
        }

        player.addToPlayerUpdatesLog(socialDecision.getInteractionResponse());
    }

    private void handlePlayerDecision(PlayerDecision playerDecision) {
        Player player = playerDecision.getPlayer();
        PlayerDecision.PlayerChoicesType choiceType = playerDecision.getPlayerChosenType();
        boolean success = ActionChance.roll(50);

        String result = success ? playerDecision.getPositiveResult() : playerDecision.getNegativeResult();
        player.addToPlayerUpdatesLog(result);

        int positiveChange = rollInt(5, 15);
        int negativeChange = rollInt(-10, - 5);

        switch (choiceType) {
            case HEALTH -> player.updateHealth(success ? positiveChange : negativeChange);
            case HAPPINESS -> player.updateHappiness(success ? positiveChange : negativeChange);
            default -> {
                LOGGER.log(Level.WARNING, "Unexpected choice type: " + choiceType);
                throw new IllegalArgumentException("Unexpected choice type: " + choiceType);
            }
        }
    }
}
