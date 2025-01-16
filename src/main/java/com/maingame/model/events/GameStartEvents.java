package com.maingame.model.events;

import static com.maingame.helper.ActionChance.rollInt;

import com.maingame.model.Player;
import com.maingame.model.NonPlayerCharacter;
import com.maingame.data.RealNameFaker;

public class GameStartEvents {

    private final Player player;

    public GameStartEvents(Player player) {
        this.player = player;
        runAllEvents();
    }

    public void runAllEvents() {
        initializeFamily();
    }

    public void initializeFamily() {
        for (int i = 0; i < 2 ; i++) {

                NonPlayerCharacter parent = new NonPlayerCharacter(
                        RealNameFaker.getFirstName(),
                        this.player.getLastName(), // Use player's last name
                        30 + rollInt(0,20),
                        NonPlayerCharacter.RelationshipStates.PARENT
                );
                this.player.getPlayerFamily().addRelation(parent);

        }

        int numberOfSiblings = rollInt(0, 5);

        for (int i = 0; i < numberOfSiblings; i++) {
            NonPlayerCharacter sibling = new NonPlayerCharacter(
                    RealNameFaker.getFirstName(),
                    this.player.getLastName(),
                    10 + rollInt(0, 10),
                    NonPlayerCharacter.RelationshipStates.SIBLING
            );
            this.player.getPlayerFamily().addRelation(sibling);
        }

        String message = "I was born into the " + this.player.getLastName() + " Family with " + numberOfSiblings +
                " siblings and " + (this.player.getPlayerFamily().listRelations().size() - numberOfSiblings) + " parents.";
        this.player.addToPlayerUpdatesLog(message);
    }
}
