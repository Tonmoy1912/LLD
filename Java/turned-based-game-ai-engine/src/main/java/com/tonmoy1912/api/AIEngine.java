package com.tonmoy1912.api;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.GameInfo;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;

public class AIEngine {
    private RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Cell suggestion;
            int threshold = 3;
            if (countMoves(board1) < threshold) {
                suggestion = getBasicMove(board1);
            } else if (countMoves(board1) < threshold + 1) {
                suggestion = getCellToPlay(player, board1);
            } else {
                suggestion = getOptimalMove(player, board1);
            }
            if (suggestion != null)
                return new Move(suggestion, player);
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

    private Cell getOptimalMove(Player player, TicTacToeBoard board) {
        // 1. if you have a winning move, then play it.
        Cell best = offensive(player, board);
        if (best != null) {
            return best;
        }
        // 2. if opp has a wining move, then block it.
        best = defensive(player, board);
        if (best != null) {
            return best;
        }
        // 3. if you have a fork, then play it
        // 4. if opp has a fork, then block it
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if (gameInfo.hasFork()) {
            best = gameInfo.getForkCell();
            return best;
        }
        // 5. if the center is available, take it
        if (board.getSymbol(1, 1) == null) {
            return new Cell(1, 1);
        }
        // 6. if the corner is available, take it
        final int[][] corners = { { 0, 0 }, { 0, 2 }, { 2, 0 }, { 2, 2 } };
        for (int i = 0; i < 4; i++) {
            if (board.getSymbol(corners[i][0], corners[i][1]) == null) {
                return new Cell(corners[i][0], corners[i][1]);
            }
        }
        return null;
    }

    private Cell getCellToPlay(Player player, TicTacToeBoard board) {
        // Victorious move
        Cell best = offensive(player, board);
        if (best != null) {
            return best;
        }
        // Defensive move
        best = defensive(player, board);
        if (best != null) {
            return best;
        }
        return getBasicMove(board);
    }

    private Cell offensive(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private Cell defensive(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private Cell getBasicMove(TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }
}
