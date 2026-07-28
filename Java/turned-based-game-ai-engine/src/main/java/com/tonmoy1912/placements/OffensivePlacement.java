package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.utils.Utils;

public class OffensivePlacement implements Placement {

    private static OffensivePlacement offensivePlacement;

    private OffensivePlacement() {

    }

    // Singleton Design Pattern
    public synchronized static OffensivePlacement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, () -> new OffensivePlacement());
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = offensive(player, board);
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }

    private Cell offensive(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }
}
