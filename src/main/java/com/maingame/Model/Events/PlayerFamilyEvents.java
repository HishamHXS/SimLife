package com.maingame.Model.Events;

import static com.maingame.Helper.ActionChance.roll;

import com.maingame.Model.NonPlayerCharacter;
import com.maingame.Model.Player;
import com.maingame.Data.RealNameFaker;
import java.util.List;
import java.util.Random;


public class PlayerFamilyEvents {
    private final Player player;

    public PlayerFamilyEvents(Player player) {
        this.player = player;
    }

    public void playerSiblingInteraction() {
        if (roll(15)) {
            List<NonPlayerCharacter> siblings = player.getPlayerFamily().listRelations();
            if (!siblings.isEmpty()) {
                NonPlayerCharacter sibling = siblings.get(new Random().nextInt(siblings.size()));
                player.addToPlayerUpdatesLog("I spent time with " + sibling.getFirstName());
                sibling.updateRelationshipLevel();
            } else {
                player.addToPlayerUpdatesLog("I wish I had a sibling.");
            }
        }
    }

    public void playerSiblingBorn() {
        if (roll(5)) {
            NonPlayerCharacter sibling = new NonPlayerCharacter(RealNameFaker.getFirstName(), player.getLastName(),
                    0, NonPlayerCharacter.RelationshipStates.SIBLING);
            player.getPlayerFamily().addRelation(sibling);
            player.addToPlayerUpdatesLog("My sibling: " + sibling.getFirstName() + " was born.");
        }
    }
}
