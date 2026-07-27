package com.tonmoy1912.game;

// Interface Segregation Principle
public interface CellBoard extends Board {
    String getSymbol(int row,int col);
}
