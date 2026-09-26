package com.minidu.chessengine.domain.entities;

import com.minidu.chessengine.domain.enums.ChessColor;
import com.minidu.chessengine.domain.enums.MatchResult;

public class Match {
    private final int boardNumber;
    private final Player whitePlayer;
    private final Player blackPlayer; // This could be null if the white player receives a Bye
    private MatchResult result;
    private boolean isCompleted;

    public Match(int boardNumber, Player whitePlayer, Player blackPlayer) {
        this.boardNumber = boardNumber;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.result = MatchResult.NOT_PLAYED;
        this.isCompleted = false;
    }

    public void submitResult(MatchResult finalResult) {
        if (this.isCompleted) {
            throw new IllegalStateException("Match result on board " + boardNumber + " is already submitted.");
        }
        
        this.result = finalResult;
        this.isCompleted = true;

        // Update the players' internal tournament scores immediately
        if (whitePlayer != null) {
            whitePlayer.recordMatchPlayed(result, ChessColor.WHITE);
        }
        if (blackPlayer != null) {
            // Note: MatchResult scores are flipped for Black internally
            blackPlayer.recordMatchPlayed(result, ChessColor.BLACK);
        }
    }

    public int getBoardNumber() { return boardNumber; }
    public Player getWhitePlayer() { return whitePlayer; }
    public Player getBlackPlayer() { return blackPlayer; }
    public MatchResult getResult() { return result; }
    public boolean isCompleted() { return isCompleted; }
}