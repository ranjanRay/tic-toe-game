package com.rajeev.strategies;

import com.rajeev.models.Board;
import com.rajeev.models.Move;
import com.rajeev.models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player);
}
