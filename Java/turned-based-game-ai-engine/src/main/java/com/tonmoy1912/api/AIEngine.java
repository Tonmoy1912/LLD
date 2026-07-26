package com.tonmoy1912.api;

import com.tonmoy1912.boards.TicTakToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;

public class AIEngine {
    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTakToeBoard) {
            TicTakToeBoard board1 = (TicTakToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) == null) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
