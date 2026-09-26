package com.minidu.chessengine.domain.algorithms;

import com.minidu.chessengine.domain.entities.Player;
import com.minidu.chessengine.domain.tournaments.Tournament;

public interface TieBreakStrategy {
    /**
     * Calculates the specific tie-break score for a given player.
     */
    double calculate(Player player, Tournament tournament);
}