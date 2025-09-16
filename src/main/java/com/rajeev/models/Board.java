package com.rajeev.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> grid;

    public Board(int size) {
        this.size = size;
        this.grid = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            grid.add(new ArrayList<>());
            for(int j = 0; j < size; j++) {
                Cell cell = new Cell(i, j);
                cell.setState(CellState.EMPTY);
                grid.get(i).add(cell);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public void display() {
        for(List<Cell> row : grid) {
            for(Cell cell : row) {
                cell.display();
            }
            System.out.println();
        }
    }
}
