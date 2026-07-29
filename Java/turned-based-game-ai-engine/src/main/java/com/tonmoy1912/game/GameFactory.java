package com.tonmoy1912.game;

import com.tonmoy1912.boards.TicTacToeBoard;

// Factory Design Pattern
public class GameFactory {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer) {
        return new Game(new GameConfig(maxTimePerMove, maxTimePerMove != null),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }

    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, TicTacToeBoard board) {
        return new Game(new GameConfig(maxTimePerMove, maxTimePerMove != null),
                board,
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);
    }
}
