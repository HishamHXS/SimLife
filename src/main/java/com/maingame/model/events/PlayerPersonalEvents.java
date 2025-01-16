package com.maingame.model.events;

import static com.maingame.helper.ActionChance.roll;
import static com.maingame.helper.ActionChance.rollInt;

import com.maingame.model.decision.Decision;
import com.maingame.model.decision.PlayerDecision;
import com.maingame.model.Player;
import com.maingame.data.SicknessFaker;

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
