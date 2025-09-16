package com.rajeev.strategies;

import com.rajeev.models.Board;
import com.rajeev.models.Move;
import com.rajeev.models.Player;
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
        System.out.println("rowMap size while adding key into the list: "+ rowMap.size());
        return count.get(symbol.getSymbol()) == board.getSize();
    }

    @Override
    public void undoMoveSideEffects(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getCell().getSymbol();
        rowMap.get(row).put(symbol.getSymbol(), rowMap.get(row).get(symbol.getSymbol()) - 1);

        System.out.println("rowMap: "+ rowMap.size() + "row: " + row + "col: " + col);
//        Map<Character, Integer> count = rowMap.get(row);
//        System.out.println("count: " + count.size());

        System.out.println("symbol: " + symbol.getSymbol());
//        int charCount = count.get(symbol.getSymbol());
//        charCount -= 1;
//        if(charCount == 0) {
//            count.remove(symbol.getSymbol());
//            return;
//        }
//        count.put(symbol.getSymbol(), charCount);
    }
}
