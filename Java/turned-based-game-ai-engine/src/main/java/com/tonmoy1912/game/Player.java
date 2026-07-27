package com.tonmoy1912.game;

/**
 * Player
 */
public class Player {
    private String playerSymbol;
    private User id;
    private int timeUsedInMillis;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol(){
        return playerSymbol;
    }

    public Player flip() {
        return new Player(playerSymbol=="0"?"X":"0");
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }

    public void setTimeUsedInMillis(int timeUsedInMillis) {
        this.timeUsedInMillis += timeUsedInMillis;
    }
}
