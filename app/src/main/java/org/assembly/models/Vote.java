package org.assembly.models;

public class Vote {
    private int proposal;
    private int phase;
    private int user;

    public Vote() {}

    public Vote(int proposal, int phase, int user) {
        this.proposal = proposal;
        this.phase = phase;
        this.user = user;
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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
