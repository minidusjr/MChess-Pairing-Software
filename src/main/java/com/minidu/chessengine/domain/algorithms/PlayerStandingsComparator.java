package com.minidu.chessengine.domain.algorithms;

import java.util.Comparator;
import java.util.List;

import com.minidu.chessengine.domain.entities.Player;
import com.minidu.chessengine.domain.tournaments.Tournament;

public class PlayerStandingsComparator implements Comparator<Player> {
    private final Tournament tournament;
    private final List<TieBreakStrategy> tieBreaks;

    public PlayerStandingsComparator(Tournament tournament, List<TieBreakStrategy> tieBreaks) {
        this.tournament = tournament;
        this.tieBreaks = tieBreaks;
    }

    @Override
    public int compare(Player p1, Player p2) {
        // 1. Primary sorting: Total Tournament Score (Descending)
        int scoreCompare = Double.compare(p2.getTournamentScore(), p1.getTournamentScore());
        if (scoreCompare != 0) return scoreCompare;

        // 2. Secondary sorting: Iterate through the configured Tie-Breaks dynamically
        for (TieBreakStrategy strategy : tieBreaks) {
            double tb1 = strategy.calculate(p1, tournament);
            double tb2 = strategy.calculate(p2, tournament);
            int tbCompare = Double.compare(tb2, tb1); // Descending
            if (tbCompare != 0) return tbCompare;
        }

        // 3. Final fallback: FIDE Rating (Descending)
        return Integer.compare(p2.getCurrentRating(), p1.getCurrentRating());
    }
}