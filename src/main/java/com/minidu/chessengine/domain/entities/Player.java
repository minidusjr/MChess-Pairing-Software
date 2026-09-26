package com.minidu.chessengine.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.minidu.chessengine.domain.enums.ChessColor;
import com.minidu.chessengine.domain.enums.MatchResult;

public class Player {
    private final String fideId; 
    private String name;
    private int currentRating;
    private double tournamentScore;
    private List<ChessColor> colorHistory;

    public Player(String fideId, String name, int currentRating) {
        this.fideId = fideId;
        this.name = name;
        this.currentRating = currentRating;
        this.tournamentScore = 0.0; // Always starts at 0
        this.colorHistory = new ArrayList<>();
    }

    public String getFideId() { return fideId; }
    public String getName() { return name; }
    public int getCurrentRating() { return currentRating; }
    public double getTournamentScore() { return tournamentScore; }
    public List<ChessColor> getColorHistory() { return colorHistory; }

    public void recordMatchPlayed(MatchResult result, ChessColor colorPlayed) {
        if (colorPlayed == ChessColor.WHITE) {
            this.tournamentScore += result.getWhiteScore();
        } else if (colorPlayed == ChessColor.BLACK) {
            this.tournamentScore += result.getBlackScore();
        } else if (colorPlayed == ChessColor.NONE) {
            // For Byes, we assume the player gets the White score value (usually 1.0 or 0.5)
            this.tournamentScore += result.getWhiteScore(); 
        }
        
        this.colorHistory.add(colorPlayed);
    }

    public void updateRatingAfterMatch(int opponentRating, double actualScore, int kFactor) {
        double expectedScore = calculateExpectedScore(opponentRating);
        int ratingChange = (int) Math.round(kFactor * (actualScore - expectedScore));
        
        this.currentRating += ratingChange;
    }

    private double calculateExpectedScore(int opponentRating) {
        double ratingDifference = (opponentRating - this.currentRating) / 400.0;
        return 1.0 / (1.0 + Math.pow(10, ratingDifference));
    }
}