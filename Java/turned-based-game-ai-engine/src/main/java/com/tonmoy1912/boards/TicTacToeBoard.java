package com.tonmoy1912.boards;

import com.tonmoy1912.game.Board;
import com.tonmoy1912.game.Cell;
import com.tonmoy1912.game.Move;

public class TicTacToeBoard implements Board {
    private String cells[][] = new String[3][3];

    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] == null)
            cells[cell.getRow()][cell.getCol()] = symbol;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j] != null ? cells[i][j] : "-";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().symbol());
    }

    // Prototype Design Pattern
    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard boardCopy= new TicTacToeBoard();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                boardCopy.cells[i][j]=cells[i][j];
            }
        }
        return boardCopy;
    }
}
