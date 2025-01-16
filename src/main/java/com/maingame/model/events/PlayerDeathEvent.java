package com.maingame.model.events;

import com.maingame.model.Player;
import com.maingame.helper.ActionChance;

public class PlayerDeathEvent {
    private final Player player;

    public PlayerDeathEvent(Player player) {
        this.player = player;
    }

    public void checkDeath() {
        if (player.getHealth() <= 0) {
            triggerDeath("I died due to poor health.");
        } else if (ActionChance.roll(1)) { // 1% chance for random death
            triggerDeath("I met an unfortunate fate.");
        }
    }

    private void triggerDeath(String cause) {
        player.addToPlayerUpdatesLog(cause);
        player.setPlayerDead();
        handleGameOver();
    }

    private void handleGameOver() {
        player.addToPlayerUpdatesLog("Game Over. You lived for " + player.getAge() + " years.");
    }
}
