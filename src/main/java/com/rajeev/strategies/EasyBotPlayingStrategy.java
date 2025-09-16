package com.rajeev.strategies;

import com.rajeev.models.*;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        // Put the symbol in the first empty place.
        Move move = new Move(player, null);
        for(List<Cell> row : board.getGrid()) {
            for(Cell cell : row) {
                if(cell.getState().equals(CellState.EMPTY)) {
                    move.setCell(cell);
                    return move;
                }
            }
        }
        return null;
    }
}
