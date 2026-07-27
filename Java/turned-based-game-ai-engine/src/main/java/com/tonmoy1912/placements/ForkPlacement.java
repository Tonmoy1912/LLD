package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
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
    public Optional<Cell> place(Board board) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'place'");
    }

    @Override
    public Placement next() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

}
