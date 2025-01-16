package com.maingame.model.career;

public abstract class Career {
    private final String title;

    public Career(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract String getInstitutionName();

    public abstract String getDetails();
}
