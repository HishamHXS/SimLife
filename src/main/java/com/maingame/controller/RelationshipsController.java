package com.maingame.controller;

import com.maingame.model.NonPlayerCharacter;
import com.maingame.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class RelationshipsController {

    @FXML
    private VBox relationshipContainer;
    @FXML
    private ListView<String> friendsListView;
    @FXML
    private ListView<String> familyListView;

    @FXML
    private void handleCloseAction() {
        MediaController.playButtonClick();

        if (relationshipContainer.getParent() instanceof VBox parentContainer) {
            parentContainer.setVisible(false);
        }
    }

    public void listRelationships(Player player) {
        List<NonPlayerCharacter> friends = player.getPlayerFriends().listRelations();
        List<NonPlayerCharacter> family = player.getPlayerFamily().listRelations();

        ObservableList<String> relationships = FXCollections.observableArrayList(
                friends.stream()
                        .map(this::formatFriendRelationship)
                        .collect(Collectors.toList())
        );
        friendsListView.setItems(relationships);

        ObservableList<String> familyRelationships = FXCollections.observableArrayList(
                family.stream()
                        .map(this::formatFamilyRelationship)
                        .collect(Collectors.toList())
        );
        familyListView.setItems(familyRelationships);
    }

    private String formatFriendRelationship(NonPlayerCharacter friend) {
        return friend.getFullName() + " - " + friend.getRelationshipLevel() + "%";
    }

    private String formatFamilyRelationship(NonPlayerCharacter familyMember) {
        return familyMember.getFullName() + " (" + familyMember.getRelationshipStatus() + ") - "
                + familyMember.getRelationshipLevel() + "%";
    }

}