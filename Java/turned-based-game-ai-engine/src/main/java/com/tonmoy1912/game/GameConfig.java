package com.tonmoy1912.game;

public class GameConfig {
    public Integer timePerMove;
    boolean timed;

    public GameConfig(Integer timePerMove, boolean timed) {
        this.timePerMove = timePerMove;
        this.timed = timed;
    }

}
