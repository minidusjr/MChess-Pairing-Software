package com.minidu.chessengine.domain.enums;

public enum MatchResult {
    WHITE_WIN(1.0, 0.0), 
    BLACK_WIN(0.0, 1.0), 
    DRAW(0.5, 0.5), 
    NOT_PLAYED(0.0, 0.0),      // For matches scheduled but not started
    BYE_WIN(1.0, 0.0),         // For a player receiving a full point Bye
    HALF_POINT_BYE(0.5, 0.0);  // For requested half-point Byes

    private final double whiteScore;
    private final double blackScore;

    MatchResult(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}