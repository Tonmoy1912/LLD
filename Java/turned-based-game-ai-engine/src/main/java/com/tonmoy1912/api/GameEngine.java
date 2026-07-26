package com.tonmoy1912.api;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.tonmoy1912.boards.TicTakToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.GameState;
import com.tonmoy1912.game.Move;

public class GameEngine {

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTakToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move) {
        if (board instanceof TicTakToeBoard) {
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GameState isComplete(Board board) {
        if (board instanceof TicTakToeBoard) {
            TicTakToeBoard board1 = (TicTakToeBoard) board;

            GameState rowWin = outerTraversal((i, j) -> board1.getSymbol(i, j));
            if (rowWin.isOver()) {
                return rowWin;
            }
            GameState colWin = outerTraversal((i, j) -> board1.getSymbol(j, i));
            if (colWin.isOver()) {
                return colWin;
            }

            GameState diagWin = traverse(i -> board1.getSymbol(i, i));
            if (diagWin.isOver()) {
                return diagWin;
            }
            GameState revDiagWin = traverse(i -> board1.getSymbol(i, 2 - i));
            if (revDiagWin.isOver()) {
                return revDiagWin;
            }

            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    private GameState traverse(Function<Integer, String> next) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int i = 0; i < 3; i++) {
            if (next.apply(i) == null || !next.apply(i).equals(next.apply(0))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(true, next.apply(0));
        }

        return result;
    }

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int ii = i;
            GameState traversal=traverse((j) -> next.apply(ii, j));
            if(traversal.isOver()){
                result=traversal;
                break;
            }
        }
        return result;
    }

}
