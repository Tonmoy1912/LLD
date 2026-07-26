package com.tonmoy1912.game;

/**
 * Player
 */
public class Player {
    private String playerSymbol;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol(){
        return playerSymbol;
    }

    public Player flip() {
        return new Player(playerSymbol=="0"?"X":"0");
    }
}
