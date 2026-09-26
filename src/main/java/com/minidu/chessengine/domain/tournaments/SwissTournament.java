package com.minidu.chessengine.domain.tournaments;

import com.minidu.chessengine.domain.entities.Round;

public class SwissTournament extends Tournament {

    public SwissTournament(String id, String name, int totalRounds) {
        super(id, name, totalRounds);
    }

    @Override
    public Round generateNextRound() {
        if (isFinished) {
            throw new IllegalStateException("Cannot pair rounds: Tournament is already finished.");
        }
        if (currentRoundNumber >= totalRounds) {
            throw new IllegalStateException("Maximum number of rounds reached.");
        }
        
        if (currentRoundNumber > 0) {
            Round previousRound = rounds.get(currentRoundNumber - 1);
            if (!previousRound.isCompleted()) {
                throw new IllegalStateException("Cannot generate next round: Previous round has unfinished matches.");
            }
        }

        currentRoundNumber++;
        Round nextRound = new Round(currentRoundNumber);

        /* 
         * PHASE 2 PLACEHOLDER: FIDE Dutch System Algorithm
         * 
         * 1. Sort 'this.players' into Score Brackets.
         * 2. Identify and handle Downfloaters/Upfloaters.
         * 3. Evaluate ColorPreferences (Absolute vs Mild).
         * 4. Create new Match() objects and add them to 'nextRound'.
         */

        this.rounds.add(nextRound);
        return nextRound;
    }
}