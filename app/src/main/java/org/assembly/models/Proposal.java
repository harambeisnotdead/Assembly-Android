package org.assembly.models;

import java.io.Serializable;

public class Proposal implements Serializable {
    private int id;
    private String title;
    private String image;
    private String close_date;
    private String description;
    private String phase;
    private int user;

    public Proposal() {}

    public Proposal(int id, String title, String image, String close_date, String description, String phase, int user) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.close_date = close_date;
        this.description = description;
        this.phase = phase;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("%d - %s - %s - %s - %s - %d\n", id, title, close_date, description, phase, user);
    }
}
