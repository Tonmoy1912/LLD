package com.tonmoy1912.game;

/**
 * GameInfo
 */
public class GameInfo {
    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;
    private int numberOfMove;
    private Cell forkCell;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int numberOfMove, Cell forCell) {
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.numberOfMove = numberOfMove;
        this.forkCell = forCell;
    }

    public boolean hasFork() {
        return hasFork;
    }

    public Cell getForkCell() {
        return forkCell;
    }
}
