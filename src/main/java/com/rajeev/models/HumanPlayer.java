package com.rajeev.models;

import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(int id, String name, Symbol symbol, PlayerType playerType) {
        super(id, name, symbol, playerType);
    }

    @Override
    public Move getMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Row: ");
        int r = scanner.nextInt();
        System.out.println("Column: ");
        int c = scanner.nextInt();

        return new Move(this, new Cell(r, c));
    }
}
