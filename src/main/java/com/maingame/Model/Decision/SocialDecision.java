package com.maingame.Model.Decision;

import com.maingame.Data.InteractionsFaker;
import com.maingame.Model.NonPlayerCharacter;
import com.maingame.Model.Player;

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
