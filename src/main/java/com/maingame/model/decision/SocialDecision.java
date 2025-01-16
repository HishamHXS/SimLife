package com.maingame.model.decision;

import com.maingame.data.InteractionsFaker;
import com.maingame.model.NonPlayerCharacter;
import com.maingame.model.Player;

public class SocialDecision extends Decision {

    private final NonPlayerCharacter nonPlayerCharacter;
    private final String interaction;
    private final String interactionType;
    private final String interactionResponse;

    public SocialDecision(NonPlayerCharacter nonPlayerCharacter, Player player) {
        super(player);
        this.nonPlayerCharacter = nonPlayerCharacter;

        String[] faker = new InteractionsFaker().InteractionsProvider().getRandomSocialInteraction();
        this.interaction = faker[0];
        this.interactionType = faker[1];
        this.interactionResponse = faker[2];
    }

    @Override
    public String getInteraction() {
        return this.nonPlayerCharacter.getFullName() + " " + this.interaction;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public String getInteractionResponse() {
        return interactionResponse.replace("{npc}", this.nonPlayerCharacter.getFirstName());
    }

    public NonPlayerCharacter getNonPlayerCharacter() {
        return this.nonPlayerCharacter;
    }
}
