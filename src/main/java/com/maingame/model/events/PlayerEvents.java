package com.maingame.model.events;

import com.maingame.model.Player;

public class PlayerEvents {
    private final PlayerPersonalEvents personalEvents;
    private final PlayerSocialEvents socialEvents;
    private final PlayerFamilyEvents familyEvents;
    private final PlayerCareerEvents careerEvents;
    private final PlayerDeathEvent deathEvent;

    public PlayerEvents(Player player) {
        this.personalEvents = new PlayerPersonalEvents(player);
        this.socialEvents = new PlayerSocialEvents(player);
        this.familyEvents = new PlayerFamilyEvents(player);
        this.careerEvents = new PlayerCareerEvents(player);
        this.deathEvent = new PlayerDeathEvent(player);
    }

    public void runAllEvents() {
        socialEvents.playerFoundMoneyEvent();
        socialEvents.playerSocialEncounter();
        socialEvents.playerRandomFriendshipChange();
        socialEvents.playerFriendInteraction();

        familyEvents.playerSiblingInteraction();
        familyEvents.playerSiblingBorn();

        personalEvents.playerSickness();
        personalEvents.playerRandomHealthChange();
        personalEvents.playerHealthDecision();
        personalEvents.playerHappinessDecision();
        personalEvents.playerRandomHappinessChange();

        careerEvents.playerSchoolUpdate();
        careerEvents.playerYearlySalary();

        deathEvent.checkDeath();
    }
}
