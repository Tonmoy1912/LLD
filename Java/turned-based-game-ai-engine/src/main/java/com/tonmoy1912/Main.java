package com.tonmoy1912;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.tonmoy1912.api.AIEngine;
import com.tonmoy1912.api.GameEngine;
import com.tonmoy1912.api.RuleEngine;
import com.tonmoy1912.boards.Board;
import com.tonmoy1912.commands.implementations.EmailCommand;
import com.tonmoy1912.commands.implementations.SMSCommand;
import com.tonmoy1912.events.ActivityEvent;
import com.tonmoy1912.events.Event;
import com.tonmoy1912.events.EventBus;
import com.tonmoy1912.events.Subscriber;
import com.tonmoy1912.events.WinEvent;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.services.EmailService;
import com.tonmoy1912.services.SMSService;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            GameEngine gameEngine = new GameEngine();
            AIEngine aiEngine = new AIEngine();
            RuleEngine ruleEngine = new RuleEngine();
            EmailService emailService = new EmailService();
            SMSService smsService = new SMSService();
            Board board = gameEngine.start("TicTacToe");
            Player computer = new Player("O");
            Player human = new Player("X");

            EventBus eventBus = new EventBus();

            eventBus.subscribe(new Subscriber((e) -> emailService.send(new EmailCommand(e))));
            eventBus.subscribe(new Subscriber((e) -> smsService.send(new SMSCommand(e))));

            if (human.getUser().activeAfter(10, TimeUnit.DAYS)) {
                eventBus.publish(new Event(human.getUser(), "Congratulation!", null, "ACTIVITY"));
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
                eventBus.publish(new Event(human.getUser(), "Congratulation!", "protocol://example.com", "WIN"));
            }

            System.out.println(board);
            System.out.println("GameResult: " + ruleEngine.getState(board));
        }
    }
}
