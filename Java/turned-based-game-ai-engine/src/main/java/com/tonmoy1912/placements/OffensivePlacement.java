package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.utils.Utils;

public class OffensivePlacement implements Placement {

    private static OffensivePlacement offensivePlacement;

    private OffensivePlacement() {

    }

    // Singleton Design Pattern
    public synchronized static OffensivePlacement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, () -> new OffensivePlacement());
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'place'");
    }

    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }

}
