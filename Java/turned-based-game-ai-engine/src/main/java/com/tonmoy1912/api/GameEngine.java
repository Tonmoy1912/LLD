package com.tonmoy1912.api;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Move;

public class GameEngine {

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move) {
        if (board instanceof TicTacToeBoard) {
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }

}