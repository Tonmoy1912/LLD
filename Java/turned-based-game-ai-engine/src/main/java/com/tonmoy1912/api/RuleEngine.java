package com.tonmoy1912.api;

import java.util.HashMap;
import java.util.Map;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.GameInfo;
import com.tonmoy1912.game.GameInfoBuilder;
import com.tonmoy1912.game.GameState;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.game.Rule;
import com.tonmoy1912.game.RuleSet;

public class RuleEngine {

    Map<String, RuleSet<TicTacToeBoard>> map = new HashMap<>();

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        map.put(key, TicTacToeBoard.getRules());
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            for (Rule<TicTacToeBoard> rule : map.get(TicTacToeBoard.class.getName())) {
                GameState gameState = rule.getCondition().apply(board1);
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
            Cell forkCell = null;
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
                                forkCell = new Cell(k, l);
                                b1.move(new Move(forkCell, player.flip()));

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
                                    .forkCell(forkCell)
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
}