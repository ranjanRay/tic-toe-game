package com.rajeev.strategies;

import com.rajeev.models.Board;
import com.rajeev.models.Move;
import com.rajeev.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy, Undoable {
    Map<Integer, Map<Character, Integer>> rowMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Character, Integer> count = new HashMap<>();
        if(rowMap.containsKey(row)) {
            count = rowMap.get(row);
        }

        if(!count.containsKey(symbol.getSymbol())) {
            count.put(symbol.getSymbol(), 0);
        }
        count.put(symbol.getSymbol(), count.get(symbol.getSymbol()) + 1);
        rowMap.put(row, count);
        return count.get(symbol.getSymbol()) == board.getSize();
    }

    @Override
    public void undoMoveSideEffects(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getCell().getSymbol();
        rowMap.get(row).put(symbol.getSymbol(), rowMap.get(row).get(symbol.getSymbol()) - 1);
    }
}
