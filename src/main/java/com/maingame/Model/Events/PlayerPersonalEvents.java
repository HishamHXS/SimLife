package com.maingame.Model.Events;

import static com.maingame.Helper.ActionChance.roll;
import static com.maingame.Helper.ActionChance.rollInt;

import com.maingame.Model.Decision.Decision;
import com.maingame.Model.Decision.PlayerDecision;
import com.maingame.Model.Player;
import com.maingame.Data.SicknessFaker;

public class PlayerPersonalEvents {
    private final Player player;

    public PlayerPersonalEvents(Player player) {
        this.player = player;
    }

    public void playerRandomHappinessChange() {
        this.player.updateHappiness(rollInt(-10, 10));
    }

    public void playerRandomHealthChange() {
        this.player.updateHealth(rollInt(-2, 5));
    }

    public void playerHealthDecision() {
        if (roll(5)) {
            Decision decision = new PlayerDecision(player, PlayerDecision.PlayerChoicesType.HEALTH);
            this.player.addToPlayerDecisionsLog(decision);
        }
    }

    public void playerHappinessDecision() {
        if ((roll(5))) {
            Decision decision = new PlayerDecision(player, PlayerDecision.PlayerChoicesType.HAPPINESS);
            this.player.addToPlayerDecisionsLog(decision);
        }
    }

    public void playerSickness() {
        if (roll(20)) {
            SicknessFaker sicknessFaker = new SicknessFaker();
            player.addToPlayerUpdatesLog("I am suffering from " + sicknessFaker.sicknessProvider().sicknessChance());
        }
    }
}
