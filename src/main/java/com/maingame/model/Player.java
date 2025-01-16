package com.maingame.model;

import com.maingame.model.career.PlayerCareer;
import com.maingame.model.decision.Decision;
import com.maingame.model.events.GameStartEvents;
import com.maingame.model.events.PlayerEvents;
import com.maingame.model.relationships.PlayerFamily;
import com.maingame.model.relationships.PlayerFriends;

import java.util.ArrayList;

import static com.maingame.helper.ActionChance.rollInt;

public class Player extends Entity{

    private final PlayerEvents playerEvents;
    private final GameStartEvents gameStartEvents;

    private final ArrayList<String> playerUpdatesLog;
    private final ArrayList<Decision> playerDecisionsLog;

    private final PlayerFriends playerFriends;
    private final PlayerFamily playerFamily;
    private final PlayerCareer playerCareer;

    private int happiness;
    private int intellect;

    public Player(String firstName, String lastName, int age, int cash, int health) {
        super(firstName, lastName, age, cash, health);
        this.happiness = rollInt(20, 80);
        this.intellect = rollInt(20, 80);

        this.playerFriends = new PlayerFriends(this);
        this.playerFamily = new PlayerFamily(this);
        this.playerCareer = new PlayerCareer(this);

        this.playerUpdatesLog = new ArrayList<>();
        this.playerDecisionsLog = new ArrayList<>();

        this.playerEvents = new PlayerEvents(this);
        this.gameStartEvents = new GameStartEvents(this);
    }

    public void updateAge() {
        this.age++;
        this.playerUpdatesLog.add("I am now " + this.getAge() + " years old.");
        playerEvents.runAllEvents();
    }

    public void setPlayerDead() {
        this.playerAlive = false;
    }

    public void updateHealth(int health) {
        this.health += health;
        this.health = checkBoundaries(this.health);
    }

    public void updateCash(int cash) {
        this.cash += cash;
    }

    public void updateHappiness(int amount) {
        this.happiness += amount;
        this.happiness = checkBoundaries(this.happiness);
    }

    public void updateIntellect(int amount) {
        this.intellect += amount;
        this.intellect = checkBoundaries(this.intellect);
    }

    public int getHappiness() {
        return this.happiness;
    }

    public int getIntellect() {
        return this.intellect;
    }

    public PlayerFriends getPlayerFriends() {
        return this.playerFriends;
    }

    public PlayerFamily getPlayerFamily() {
        return this.playerFamily;
    }

    public PlayerCareer getPlayerCareer() {
        return playerCareer;
    }

    public ArrayList<String> getPlayerUpdatesLog() {
        return playerUpdatesLog;
    }

    public void addToPlayerUpdatesLog(String message) {
        this.playerUpdatesLog.add(message);
    }

    public void clearPlayerUpdatesLog() {
        this.playerUpdatesLog.clear();
    }

    public ArrayList<Decision> getPlayerDecisionLog() {
        return this.playerDecisionsLog;
    }

    public void addToPlayerDecisionsLog(Decision decision) {
        this.playerDecisionsLog.add(decision);
    }

    public void clearPlayerDecisionsLog() {
        this.playerDecisionsLog.clear();
    }

    private int checkBoundaries(int number) {
        return Math.max(0, Math.min(number, 100));
    }
}