package com.tonmoy1912;

import org.junit.Test;

import com.tonmoy1912.game.Game;
import com.tonmoy1912.game.GameFactory;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeoutTest() {
        Game game = gameFactory.createGame(3, 120);
    }

    @Test
    public void timeoutTestPerPlayer() {
        Game game = gameFactory.createGame(null, 120);
    }
}
