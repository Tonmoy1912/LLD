package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.utils.Utils;

public class DefensivePlacement implements Placement {

    private static DefensivePlacement defensivePlacement;

    private DefensivePlacement() {

    }

    // Singleton Design Pattern
    public synchronized static DefensivePlacement get() {
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, () -> new DefensivePlacement());
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'place'");
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }

}
