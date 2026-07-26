package com.tonmoy1912;

import java.util.Scanner;

import com.tonmoy1912.api.AIEngine;
import com.tonmoy1912.api.GameEngine;
import com.tonmoy1912.api.RuleEngine;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            GameEngine gameEngine = new GameEngine();
            AIEngine aiEngine = new AIEngine();
            RuleEngine ruleEngine = new RuleEngine();
            Board board = gameEngine.start("TicTacToe");

            while (!ruleEngine.getState(board).isOver()) {
                Player computer = new Player("0");
                Player opponent = new Player("X");

                System.out.println("Make your move");
                System.out.println(board);

                int row = scanner.nextInt();
                int col = scanner.nextInt();
                Move oppMove = new Move(new Cell(row, col), opponent);
                gameEngine.move(board, oppMove);

                if (!ruleEngine.getState(board).isOver()) {
                    Move computerMove = aiEngine.suggestMove(computer, board);
                    gameEngine.move(board, computerMove);
                }
            }

            System.out.println(board);
            System.out.println("GameResult: " + gameEngine.isComplete(board));
        }
    }
}
