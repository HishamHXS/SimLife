package com.maingame.Model.Events;

import static com.maingame.Helper.ActionChance.roll;
import static com.maingame.Helper.ActionChance.rollInt;

import com.maingame.Model.Decision.Decision;
import com.maingame.Model.Decision.SocialDecision;
import com.maingame.Model.NonPlayerCharacter;
import com.maingame.Model.Player;
import com.maingame.Data.RealNameFaker;
import java.util.List;

public class PlayerSocialEvents {
    private final Player player;

    public PlayerSocialEvents(Player player) {
        this.player = player;
    }

    public void playerFoundMoneyEvent() {
        if (roll(20)) {
            int cashEarned = rollInt(0, 1000);
            this.player.updateCash(cashEarned);
            this.player.addToPlayerUpdatesLog("I have found " + cashEarned + " dabloons");
        }
    }

    public void playerRandomFriendshipChange() {
        if (roll(20)) {
            List<NonPlayerCharacter> friends = this.player.getPlayerFriends().listRelations();
            if (!friends.isEmpty()) {
                NonPlayerCharacter randomFriend = friends.get(rollInt(0, friends.size()));
                randomFriend.updateRelationshipLevel();
            }
        }
    }

    public void playerFriendInteraction() {
        if (roll(10)) {
            List<NonPlayerCharacter> playerFriendsList = this.player.getPlayerFriends().listRelations();
            if (!playerFriendsList.isEmpty()) {
                NonPlayerCharacter friend = playerFriendsList.get(rollInt(0, playerFriendsList.size()));
                this.player.addToPlayerUpdatesLog("I hung out with " + friend.getFirstName());

                // Bug
                this.player.updateHappiness(rollInt(-15, 15));

                friend.updateRelationshipLevel();
            } else {
                this.player.addToPlayerUpdatesLog("I don't have any friends to hang out with.");
            }
        }
    }

    public void playerSocialEncounter() {
        if (roll(30)) {
            NonPlayerCharacter npc = new NonPlayerCharacter(RealNameFaker.getFirstName(), RealNameFaker.getLastName(),
                    this.player.getAge());
            Decision decisionInteraction = new SocialDecision(npc, this.player);
            this.player.addToPlayerDecisionsLog(decisionInteraction);
        }
    }
}
