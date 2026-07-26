package com.tonmoy1912.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.tonmoy1912.boards.TicTakToeBoard;
import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.GameState;

public class RuleEngine {

    Map<String,List<Rule<Board>>> map= new HashMap<>();

    public RuleEngine(){
        String key=TicTakToeBoard.class.getName();
        map.put(key, new ArrayList<Rule<Board>>());
        map.get(key).add(new Rule<Board>(board->outerTraversal((i, j) -> ((TicTakToeBoard)board).getSymbol(i, j))));
        map.get(key).add(new Rule<Board>(board->outerTraversal((i, j) -> ((TicTakToeBoard)board).getSymbol(j, i))));
        map.get(key).add(new Rule<Board>(board->traverse(i -> ((TicTakToeBoard)board).getSymbol(i, i))));
        map.get(key).add(new Rule<Board>(board->traverse(i -> ((TicTakToeBoard)board).getSymbol(i, 2 - i))));
        map.get(key).add(new Rule<Board>(board->{
            TicTakToeBoard board1=(TicTakToeBoard)board;
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }
        }));
    }

    public GameState getState(Board board) {
        if (board instanceof TicTakToeBoard) {
            TicTakToeBoard board1 = (TicTakToeBoard) board;

            for(Rule<Board> rule: map.get(TicTakToeBoard.class.getName())){
                GameState gameState=rule.condition.apply(board1);
                if(gameState.isOver()){
                    return gameState;
                }
            }
            return new GameState(false, "-");

        } else {
            throw new IllegalArgumentException();
        }
    }


    private GameState traverse(Function<Integer, String> next) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int i = 0; i < 3; i++) {
            if (next.apply(i) == null || !next.apply(i).equals(next.apply(0))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(true, next.apply(0));
        }

        return result;
    }

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int ii = i;
            GameState traversal=traverse((j) -> next.apply(ii, j));
            if(traversal.isOver()){
                result=traversal;
                break;
            }
        }
        return result;
    }

}

class  Rule<T extends Board> {
    Function<T,GameState> condition;

    public Rule(Function<T,GameState> condition){
        this.condition=condition;
    }
}