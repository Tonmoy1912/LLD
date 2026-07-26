package com.tonmoy1912.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.GameInfo;
import com.tonmoy1912.game.GameInfoBuilder;
import com.tonmoy1912.game.GameState;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;

public class RuleEngine {

    Map<String, List<Rule<Board>>> map = new HashMap<>();

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        map.put(key, new ArrayList<Rule<Board>>());
        map.get(key).add(new Rule<Board>(board -> outerTraversal((i, j) -> ((TicTacToeBoard) board).getSymbol(i, j))));
        map.get(key).add(new Rule<Board>(board -> outerTraversal((i, j) -> ((TicTacToeBoard) board).getSymbol(j, i))));
        map.get(key).add(new Rule<Board>(board -> traverse(i -> ((TicTacToeBoard) board).getSymbol(i, i))));
        map.get(key).add(new Rule<Board>(board -> traverse(i -> ((TicTacToeBoard) board).getSymbol(i, 2 - i))));
        map.get(key).add(new Rule<Board>(board -> {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
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
        }));
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            for (Rule<Board> rule : map.get(TicTacToeBoard.class.getName())) {
                GameState gameState = rule.condition.apply(board1);
                if (gameState.isOver()) {
                    return gameState;
                }
            }
            return new GameState(false, "-");

        } else {
            throw new IllegalArgumentException();
        }
    }

    public GameInfo getInfo(Board board) {
        if (board instanceof TicTacToeBoard) {
            GameState gameState = getState(board);
            final String[] players = new String[] { "X", "O" };

            for (int index = 0; index < 2; index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        b.move(new Move(new Cell(i, j), player));

                        boolean canStillWin = false;

                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b1 = b.copy();
                                b1.move(new Move(new Cell(k, l), player.flip()));

                                if (getState(b1).getWinner().equals(player.flip().symbol())) {
                                    canStillWin = true;
                                    break;
                                }
                            }

                            if (canStillWin) {
                                break;
                            }
                        }

                        if (canStillWin) {
                            // builder design pattern
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player.flip())
                                    .build();
                        }
                    }
                }
            }

            // builder design pattern
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .hasFork(false)
                    .build();
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
            GameState traversal = traverse((j) -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

}

class Rule<T extends Board> {
    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }
}