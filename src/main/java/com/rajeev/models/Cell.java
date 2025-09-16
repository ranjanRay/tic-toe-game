package com.rajeev.models;

public class Cell {
    private int row;
    private int col;
    private CellState state;
    private Symbol symbol;


    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void display() {
        if(symbol == null) {
            System.out.print("| - |");
        } else {
            System.out.print("| " + symbol.getSymbol() + " |");
        }
    }
}
