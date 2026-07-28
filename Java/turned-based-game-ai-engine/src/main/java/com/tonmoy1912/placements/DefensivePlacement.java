package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.utils.Utils;

public class DefensivePlacement implements Placement {

    private static DefensivePlacement defensivePlacement;

    private DefensivePlacement() {

    }

    // Singleton Design Pattern
    public synchronized static DefensivePlacement get() {
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, () -> new DefensivePlacement());
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = defensive(player, board);
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }

    private Cell defensive(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
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
