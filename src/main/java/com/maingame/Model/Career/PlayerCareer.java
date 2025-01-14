package com.maingame.Model.Career;


import com.maingame.Model.Player;

import java.util.HashSet;

public class PlayerCareer {

    public enum PlayerCareerStatus {
        STUDENT,
        UNEMPLOYED,
        EMPLOYED
    }

    private Player player;
    private PlayerCareerStatus currentCareerStatus;
    private Career currentCareer;
    private HashSet<String> playerResume;

    public PlayerCareer(Player player) {
        this.player = player;
        this.currentCareerStatus = PlayerCareerStatus.UNEMPLOYED;
        this.currentCareer = null;
        this.playerResume = new HashSet<>();
    }

    public void updateCurrentCareer(Career newCareer) {
        switch (newCareer) {
            case null -> {
                this.currentCareer = null;
                this.currentCareerStatus = PlayerCareerStatus.UNEMPLOYED;
            }
            case Job job -> {
                this.currentCareer = newCareer;
                this.currentCareerStatus = PlayerCareerStatus.EMPLOYED;
                this.playerResume.add(job.getTitle());
                this.player.addToPlayerUpdatesLog("I started a " + job.getTitle() + " job at " +
                        job.getInstitutionName() + " earning " + job.getSalary());
            }
            case Student student -> {
                this.currentCareer = newCareer;
                this.currentCareerStatus = PlayerCareerStatus.STUDENT;
                this.playerResume.add(student.getDegree());
                this.player.addToPlayerUpdatesLog("I started a " + student.getDegree() + " course at " +
                        student.getInstitutionName());

            }
            default -> {
            }
        }
    }

    public PlayerCareerStatus getCurrentCareerStatus() {
        return currentCareerStatus;
    }

    public Career getCurrentCareer() {
        return currentCareer;
    }

    public HashSet<String> getPlayerResume() {
        return this.playerResume;
    }
}

