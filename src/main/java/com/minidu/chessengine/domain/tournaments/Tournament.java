package com.minidu.chessengine.domain.tournaments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.minidu.chessengine.domain.entities.Player;
import com.minidu.chessengine.domain.entities.Round;

public abstract class Tournament {
    protected final String id;
    protected final String name;
    protected final int totalRounds;
    protected int currentRoundNumber;
    protected final List<Player> players;
    protected final List<Round> rounds;
    protected boolean isFinished;

    public Tournament(String id, String name, int totalRounds) {
        this.id = id;
        this.name = name;
        this.totalRounds = totalRounds;
        this.currentRoundNumber = 0;
        this.players = new ArrayList<>();
        this.rounds = new ArrayList<>();
        this.isFinished = false;
    }

    public void registerPlayer(Player player) {
        if (currentRoundNumber > 0) {
            throw new IllegalStateException("Registration is closed. The tournament has already started.");
        }
        this.players.add(player);
    }

    public void closeTournament() {
        if (currentRoundNumber < totalRounds) {
            throw new IllegalStateException("Cannot close the tournament before all rounds are completed.");
        }
        this.isFinished = true;
    }

    public abstract Round generateNextRound();

    public String getName() { return name; }
    public int getCurrentRoundNumber() { return currentRoundNumber; }
    public boolean isFinished() { return isFinished; }
    public List<Player> getPlayers() { return Collections.unmodifiableList(players); }
    public List<Round> getRounds() { return Collections.unmodifiableList(rounds); }
}