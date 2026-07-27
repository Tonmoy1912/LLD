package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;

public interface Placement {
    public Optional<Cell> place(Board board);

    public Placement next();
}
