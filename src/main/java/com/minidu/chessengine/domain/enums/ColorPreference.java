package com.minidu.chessengine.domain.enums;

public enum ColorPreference {
    ABSOLUTE_WHITE, // Has played Black twice in a row, MUST play White
    ABSOLUTE_BLACK, // Has played White twice in a row, MUST play Black
    MILD_WHITE,     // Played Black last round, prefers White
    MILD_BLACK,     // Played White last round, prefers Black
    NEUTRAL         // First round, or colors are perfectly balanced
}
