package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.GameInfo;
import com.tonmoy1912.game.Player;
import com.tonmoy1912.utils.Utils;

public class ForkPlacement implements Placement {

    private static ForkPlacement forkPlacement;

    private ForkPlacement() {

    }

    // Singleton Design Pattern
    public synchronized static ForkPlacement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, () -> new ForkPlacement());
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if (gameInfo.hasFork()) {
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CenterPlacement.get();
    }

}
