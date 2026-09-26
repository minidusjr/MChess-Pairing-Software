package com.minidu.chessengine.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Round {
    private final int roundNumber;
    private final List<Match> matches;
    private boolean isCompleted;

    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.matches = new ArrayList<>();
        this.isCompleted = false;
    }

    public void addMatch(Match match) {
        if (isCompleted) {
            throw new IllegalStateException("Cannot add matches to a completed round.");
        }
        this.matches.add(match);
    }

    public void closeRound() {
        // FIDE validation: A round cannot close if matches are still playing
        for (Match match : matches) {
            if (!match.isCompleted()) {
                throw new IllegalStateException("Board " + match.getBoardNumber() + " is still playing.");
            }
        }
        this.isCompleted = true;
    }

    public int getRoundNumber() { return roundNumber; }
    public boolean isCompleted() { return isCompleted; }
    
    // Return a read-only view of the matches to protect encapsulation
    public List<Match> getMatches() { 
        return Collections.unmodifiableList(matches); 
    }
}