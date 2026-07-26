package com.tonmoy1912.game;

/**
 * Board
 */
public interface Board {
    abstract public void move(Move move);
    abstract public Board copy();
}
