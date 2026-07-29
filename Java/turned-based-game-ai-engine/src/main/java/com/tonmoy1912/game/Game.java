package com.tonmoy1912.game;

import com.tonmoy1912.boards.Board;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    private Player winner;
    private Integer lastMoveTimeInMillis;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;

    public Game(GameConfig gameConfig, Board board, Player winner, Integer lastMoveTimeInMillis,
            Integer maxTimePerPlayer, Integer maxTimePerMove) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
    }

    public void move(Move move, int timestampinMillis) {
        int timeTakenSinceLastMove = timestampinMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeUsedInMillis(timeTakenSinceLastMove);
        if (gameConfig.timed) {
            moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        final int currentTime, endTime;
        if (gameConfig.timePerMove != null) {
            currentTime = timeTakenSinceLastMove;
            endTime = maxTimePerMove;
        } else {
            currentTime = move.getPlayer().getTimeUsedInMillis();
            endTime = maxTimePerPlayer;
        }
        if (currentTime < endTime) {
            board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }
}
