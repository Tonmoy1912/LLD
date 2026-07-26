package com.tonmoy1912.api;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;

public class AIEngine {
    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move suggestion;
            int threshold = 3;
            if (countMoves(board1) < threshold) {
                suggestion = getBasicMove(player, board1);
            } else {
                suggestion = getSmartMove(player, board1);
            }
            if (suggestion != null)
                return suggestion;
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int countMoves(TicTacToeBoard board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }

    private Move getSmartMove(Player player, TicTacToeBoard board) {
        RuleEngine ruleEngine=new RuleEngine();

        // Victorious move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move= new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy= board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return move;
                    }
                }
            }
        }

        // Defensive move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move= new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy= board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return getBasicMove(player, board);
    }

    private Move getBasicMove(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), player);
                }
            }
        }
        return null;
    }
}
