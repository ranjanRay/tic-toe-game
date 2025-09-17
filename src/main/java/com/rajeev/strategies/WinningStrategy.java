package com.rajeev.strategies;

import com.rajeev.models.Board;
import com.rajeev.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
