package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.utils.Utils;

/**
 * CenterPlacement
 */
public class CenterPlacement implements Placement {

    private static CenterPlacement centerPlacement;

    private CenterPlacement() {

    }

    // Singleton Design Pattern
    public synchronized static CenterPlacement get() {
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, () -> new CenterPlacement());
        return centerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        if (board.getSymbol(1, 1) == null) {
            best = new Cell(1, 1);
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }

}
