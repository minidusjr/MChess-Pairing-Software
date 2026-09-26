package com.minidu.chessengine.domain.algorithms;

import com.minidu.chessengine.domain.entities.Match;
import com.minidu.chessengine.domain.entities.Player;
import com.minidu.chessengine.domain.entities.Round;
import com.minidu.chessengine.domain.enums.MatchResult;
import com.minidu.chessengine.domain.tournaments.Tournament;

public class SonnebornBergerStrategy implements TieBreakStrategy {

    @Override
    public double calculate(Player player, Tournament tournament) {
        double sbScore = 0.0;

        for (Round round : tournament.getRounds()) {
            for (Match match : round.getMatches()) {
                if (!match.isCompleted()) continue;

                boolean isWhite = player.equals(match.getWhitePlayer());
                boolean isBlack = player.equals(match.getBlackPlayer());

                if (!isWhite && !isBlack) continue;

                Player opponent = isWhite ? match.getBlackPlayer() : match.getWhitePlayer();
                if (opponent == null) continue; // Skip byes

                // Apply SB logic based on the match result
                MatchResult result = match.getResult();
                double pointsEarned = isWhite ? result.getWhiteScore() : result.getBlackScore();

                if (pointsEarned == 1.0) {
                    sbScore += opponent.getTournamentScore(); // Full opponent score for a win
                } else if (pointsEarned == 0.5) {
                    sbScore += (opponent.getTournamentScore() / 2.0); // Half opponent score for a draw
                }
            }
        }
        return sbScore;
    }
}