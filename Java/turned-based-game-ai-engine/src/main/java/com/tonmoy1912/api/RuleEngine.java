package com.tonmoy1912.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.tonmoy1912.boards.Board;
import com.tonmoy1912.boards.CellBoard;
import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.boards.TicTacToeBoard.Symbol;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.GameInfo;
import com.tonmoy1912.game.GameInfoBuilder;
import com.tonmoy1912.game.GameState;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.placements.DefensivePlacement;
import com.tonmoy1912.placements.OffensivePlacement;

public class RuleEngine {

    Map<String, RuleSet> map = new HashMap<>();

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        map.put(key, TicTacToeBoard.getRules());
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            for (Rule rule : map.get(TicTacToeBoard.class.getName())) {
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

    public GameInfo getInfo(CellBoard board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(board);
            for (Symbol symbol : TicTacToeBoard.Symbol.values()) {
                Player player = new Player(symbol.marker());
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTacToeBoard.getSymbol(i, j)==null){
                            TicTacToeBoard b = ticTacToeBoard.move(new Move(new Cell(i, j), player));
                            // force opponent to make a defensive move
                            // we still win after the move
                            DefensivePlacement defensivePlacement=DefensivePlacement.get();
                            Optional<Cell> defensiveCell= defensivePlacement.place(b, player.flip());
                            if(defensiveCell.isPresent()){
                                b=b.move(new Move(defensiveCell.get(), player.flip()));
                                OffensivePlacement offensivePlacement=OffensivePlacement.get();
                                Optional<Cell> offensiveCell=offensivePlacement.place(b, player);
                                if(offensiveCell.isPresent()){
                                    return new GameInfoBuilder()
                                        .isOver(gameState.isOver())
                                        .winner(gameState.getWinner())
                                        .hasFork(true)
                                        .forkCell(new Cell(i, j))
                                        .player(player)
                                        .build();
                                }
                            }
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