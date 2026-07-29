package com.tonmoy1912.game;

/**
 * Player
 */
public class Player {
    private String playerSymbol;
    private User user;
    private int timeUsedInMillis;

    public Player(String playerSymbol) {
        user = new User();
        this.playerSymbol = playerSymbol;
    }

    public String symbol() {
        return playerSymbol;
    }

    public Player flip() {
        return new Player(playerSymbol == "O" ? "X" : "O");
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }

    public User getUser() {
        return user;
    }

    public void setTimeUsedInMillis(int timeUsedInMillis) {
        this.timeUsedInMillis += timeUsedInMillis;
    }
}
