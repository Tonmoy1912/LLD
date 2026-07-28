package com.tonmoy1912.boards;

import com.tonmoy1912.game.Move;

/**
 * Board
 */
public interface Board {
    abstract public void move(Move move);
    abstract public Board copy();
}
