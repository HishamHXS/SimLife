package com.maingame.model.decision;

import com.maingame.data.InteractionsFaker;
import com.maingame.model.Player;

public class PlayerDecision extends Decision {

    public enum PlayerChoicesType {
        HEALTH,
        HAPPINESS
    }

    private final PlayerChoicesType playerChosenType;
    private final String interaction;
    private final String positiveResult;
    private final String negativeResult;

    public PlayerDecision(Player player, PlayerChoicesType playerChoicesType) {
        super(player);
        this.playerChosenType = playerChoicesType;

        String[] faker = new InteractionsFaker().InteractionsProvider().getRandomPlayerInteraction(this.playerChosenType);
        this.interaction = faker[0];
        this.positiveResult = faker[1];
        this.negativeResult = faker[2];
    }

    @Override
    public String getInteraction() {
        return this.interaction;
    }

    public String getPositiveResult() {
        return this.positiveResult;
    }

    public String getNegativeResult() {
        return this.negativeResult;
    }

    public PlayerChoicesType getPlayerChosenType() {
        return this.playerChosenType;
    }
}
