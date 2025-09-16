package com.rajeev.strategies;

import com.rajeev.models.Move;

public interface Undoable {
    void undoMoveSideEffects(Move move);
}
