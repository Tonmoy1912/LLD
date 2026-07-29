package com.tonmoy1912;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.tonmoy1912.api.AIEngine;
import com.tonmoy1912.api.GameEngine;
import com.tonmoy1912.api.RuleEngine;
import com.tonmoy1912.boards.Board;
import com.tonmoy1912.commands.builders.SendEmailCommandBuilder;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.services.EmailService;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            GameEngine gameEngine = new GameEngine();
            AIEngine aiEngine = new AIEngine();
            RuleEngine ruleEngine = new RuleEngine();
            EmailService emailService = new EmailService();
            Board board = gameEngine.start("TicTacToe");
            Player computer = new Player("O");
            Player human = new Player("X");

            if (human.getUser().activeAfter(10, TimeUnit.DAYS)) {
                emailService.send(new SendEmailCommandBuilder()
                        .receiver(human.getUser())
                        .message("Welcome back!")
                        .build());
            }

            while (!ruleEngine.getState(board).isOver()) {

                System.out.println("Make your move");
                System.out.println(board);

                int row = scanner.nextInt();
                int col = scanner.nextInt();
                Move oppMove = new Move(new Cell(row, col), human);
                gameEngine.move(board, oppMove);

                if (!ruleEngine.getState(board).isOver()) {
                    Move computerMove = aiEngine.suggestMove(computer, board);
                    gameEngine.move(board, computerMove);
                }
            }

            if (ruleEngine.getState(board).getWinner().equals(human.symbol())) {
                emailService.send(new SendEmailCommandBuilder()
                        .receiver(human.getUser())
                        .message("Congratulation!")
                        .build());
            }

            System.out.println(board);
            System.out.println("GameResult: " + ruleEngine.getState(board));
        }
    }
}
