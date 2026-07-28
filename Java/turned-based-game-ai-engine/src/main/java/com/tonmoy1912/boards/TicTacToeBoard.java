package com.tonmoy1912.boards;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.tonmoy1912.api.Rule;
import com.tonmoy1912.api.RuleSet;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.GameState;
import com.tonmoy1912.game.Move;

public class TicTacToeBoard implements CellBoard {
    private String cells[][] = new String[3][3];
    private History history = new History();

    public static RuleSet getRules() {
        RuleSet ruleSet = new RuleSet();
        ruleSet.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        ruleSet.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        ruleSet.add(new Rule(board -> traverse(i -> board.getSymbol(i, i))));
        ruleSet.add(new Rule(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        ruleSet.add(new Rule(board -> {
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }
        }));
        return ruleSet;
    }

    @Override
    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] == null)
            cells[cell.getRow()][cell.getCol()] = symbol;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j] != null ? cells[i][j] : "-";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public TicTacToeBoard move(Move move) {
        history.add(new Representation(this));
        TicTacToeBoard board = copy();
        board.setCell(move.getCell(), move.getPlayer().symbol());
        return board;
    }

    // Prototype Design Pattern
    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard boardCopy = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardCopy.cells[i][j] = cells[i][j];
            }
        }
        // Flyway Design Pattern - Storing the common data in a single place
        boardCopy.history = history;
        return boardCopy;
    }

    private static GameState traverse(Function<Integer, String> next) {
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

    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int ii = i;
            GameState traversal = traverse((j) -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

}

// MOMENTO Design Pattern
class History {
    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex) {
        while (boards.size() - 1 > moveIndex) {
            boards.removeLast();
        }
        return boards.getLast();
    }

    public Representation undo() {
        if (boards.isEmpty()) {
            throw new IllegalStateException();
        }
        boards.removeLast();
        return boards.getLast();
    }

    public void add(Representation representation) {
        boards.add(representation);
    }
}

// Proxy Design pattern
// Act as proxy for TicTacToe Board
class Representation {
    String representation;

    public Representation(TicTacToeBoard board) {
        representation = board.toString();
    }
}