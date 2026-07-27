package com.tonmoy1912.placements;

import java.util.Optional;

import com.tonmoy1912.api.RuleEngine;
import com.tonmoy1912.boards.TicTacToeBoard;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Player;

public interface Placement {
    RuleEngine ruleEngine=new RuleEngine();

    public Optional<Cell> place(TicTacToeBoard board,Player player);

    public Placement next();
}
