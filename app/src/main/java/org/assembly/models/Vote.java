package org.assembly.models;

import android.support.v7.app.AppCompatActivity;

public class Vote {
    private int proposal;
    private int phase;
    private int vote;

    public Vote() {}

    public Vote(int proposal, int phase, int vote) {
        this.proposal = proposal;
        this.phase = phase;
        this.vote = vote;
    }

    public int getProposal() {
        return proposal;
    }

    public void setProposal(int proposal) {
        this.proposal = proposal;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
