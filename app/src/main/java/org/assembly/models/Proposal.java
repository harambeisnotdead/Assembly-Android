package org.assembly.models;

import java.io.Serializable;

public class Proposal implements Serializable {
    private int id;
    private String title;
    private String image;
    private String close_date;
    private String description;
    private String phase;
    private int comment_count;
    private int vote_votes_count;

    private int user;

    public Proposal() {
    }

    public Proposal(int id, String title, String image, String close_date, String description,
                    String phase, int comment_count, int vote_votes_count, int user) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.close_date = close_date;
        this.description = description;
        this.phase = phase;
        this.comment_count = comment_count;
        this.vote_votes_count = vote_votes_count;
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

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getVote_votes_count() {
        return vote_votes_count;
    }

    public void setVote_votes_count(int vote_votes_count) {
        this.vote_votes_count = vote_votes_count;
    }
}
