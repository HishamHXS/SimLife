package com.maingame.model;


public abstract class Entity {

    protected String firstName;
    protected String lastName;
    protected int age;
    protected int cash;
    protected int health;
    protected boolean playerAlive;

    public Entity(String firstName, String lastName, int age, int cash, int health) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cash = cash;
        this.health = health;
        this.playerAlive = true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    public int getCash() {
        return cash;
    }

    public int getHealth(){
        return health;
    }

    public boolean getPlayerAlive() {
        return playerAlive;
    }
}
