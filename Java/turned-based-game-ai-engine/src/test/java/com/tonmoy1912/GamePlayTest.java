package com.tonmoy1912;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.tonmoy1912.api.AIEngine;
import com.tonmoy1912.api.GameEngine;
import com.tonmoy1912.api.RuleEngine;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;

/**
 * Unit test for simple App.
 */
public class GamePlayTest {
    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {

        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayMoves = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        int[][] secondPlayerMove = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };

        playGame(board, firstPlayMoves, secondPlayerMove);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForColWin() {

        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayMoves = new int[][] { { 0, 1 }, { 1, 1 }, { 2, 1 } };
        int[][] secondPlayerMove = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 2 } };

        playGame(board, firstPlayMoves, secondPlayerMove);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagWin() {

        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayMoves = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
        int[][] secondPlayerMove = new int[][] { { 0, 1 }, { 1, 0 }, { 2, 1 } };

        playGame(board, firstPlayMoves, secondPlayerMove);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayMoves = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
        int[][] secondPlayerMove = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 1 } };

        playGame(board, firstPlayMoves, secondPlayerMove);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForComputerWin() {

        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayMoves = new int[][] { { 0, 0 }, { 0, 1 }, { 2, 0 } };
        int[][] secondPlayerMove = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };

        playGame(board, firstPlayMoves, secondPlayerMove);

        assertTrue(ruleEngine.getState(board).isOver());
        assertEquals("0", ruleEngine.getState(board).getWinner());
    }

    private void playGame(Board board, int[][] firstPlayMoves, int[][] secondPlayerMove) {
        int next = 0;

        while (!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("0");
            Player opponent = new Player("X");

            int row = firstPlayMoves[next][0];
            int col = firstPlayMoves[next][1];

            Move oppMove = new Move(new Cell(row, col), opponent);
            gameEngine.move(board, oppMove);

            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = new Move(new Cell(secondPlayerMove[next][0], secondPlayerMove[next][1]), computer);
                gameEngine.move(board, computerMove);
            }
            next++;
        }
    }
}
