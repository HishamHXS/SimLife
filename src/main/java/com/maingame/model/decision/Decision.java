package com.maingame.model.decision;


import com.maingame.model.Player;

public abstract class Decision {

    protected Player player;

    public Decision(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public abstract String getInteraction();
}
