package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.utils.Utils;

/**
 * CornerPlacement
 */
public class CornerPlacement implements Placement {

    private static CornerPlacement cornerPlacement;

    private CornerPlacement() {

    }

    // Singleton Design Pattern
    public synchronized static CornerPlacement get() {
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, () -> new CornerPlacement());
        return cornerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        final int[][] corners = { { 0, 0 }, { 0, 2 }, { 2, 0 }, { 2, 2 } };
        for (int i = 0; i < 4; i++) {
            if (board.getSymbol(corners[i][0], corners[i][1]) == null) {
                best = new Cell(corners[i][0], corners[i][1]);
                break;
            }
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return null;
    }

}
