package org.assembly;

import android.media.Image;

public class Proposal {
    private int id;
    private String title;
    private String description;
    private Image image;
    private String phase;
    private int user;

    public Proposal() { }

    public Proposal(int id, String title, String description, Image image, String phase, int user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.phase = phase;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Proposal setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Proposal setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Proposal setDescription(String description) {
        this.description = description;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Proposal setImage(Image image) {
        this.image = image;
        return this;
    }

    public String getPhase() {
        return phase;
    }

    public Proposal setPhase(String phase) {
        this.phase = phase;
        return this;
    }

    public int getUser() {
        return user;
    }

    public Proposal setUser(int user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d - %s - %s - %s - %d\n", id, title, description, phase, user);
    }
}
