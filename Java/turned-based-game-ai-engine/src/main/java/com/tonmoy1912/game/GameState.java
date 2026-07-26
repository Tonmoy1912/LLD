package com.tonmoy1912.game;

/**
 * GameResult
 */
public class GameState {
    boolean isOver;
    String winner;

    public GameState(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public boolean isOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "{ isOver: "+isOver+", winner: "+winner+" }";
    }
    
}
