package com.minidu.chessengine.domain.algorithms;

import com.minidu.chessengine.domain.entities.Match;
import com.minidu.chessengine.domain.entities.Player;
import com.minidu.chessengine.domain.entities.Round;
import com.minidu.chessengine.domain.tournaments.Tournament;

public class BuchholzStrategy implements TieBreakStrategy {

    @Override
    public double calculate(Player player, Tournament tournament) {
        double buchholzScore = 0.0;

        for (Round round : tournament.getRounds()) {
            for (Match match : round.getMatches()) {
                if (!match.isCompleted()) continue;

                // Find the opponent in this match
                Player opponent = null;
                if (match.getWhitePlayer() != null && match.getWhitePlayer().getFideId().equals(player.getFideId())) {
                    opponent = match.getBlackPlayer();
                } else if (match.getBlackPlayer() != null && match.getBlackPlayer().getFideId().equals(player.getFideId())) {
                    opponent = match.getWhitePlayer();
                }

                // Add the opponent's total tournament score to our Buchholz
                if (opponent != null) {
                    buchholzScore += opponent.getTournamentScore();
                }
            }
        }
        return buchholzScore;
    }
}