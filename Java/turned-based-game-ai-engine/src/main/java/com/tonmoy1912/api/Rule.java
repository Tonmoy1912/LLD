package com.tonmoy1912.api;

import java.util.function.Function;

import com.tonmoy1912.boards.CellBoard;
import com.tonmoy1912.game.GameState;

public class Rule {
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }

    public Function<CellBoard, GameState> getCondition() {
        return condition;
    }
}
