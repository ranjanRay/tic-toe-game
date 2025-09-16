package com.rajeev.models;

import com.rajeev.strategies.BotPlayingStrategy;
import com.rajeev.strategies.BotPlayingStrategyFactory;

public class BotPlayer extends Player {
    private BotDifficultyLevel difficulty;
    private BotPlayingStrategy strategy;
    public BotPlayer(int id, String name, Symbol symbol, PlayerType playerType, BotDifficultyLevel difficulty) {
        super(id, name, symbol, playerType);
        this.difficulty = difficulty;
        this.strategy = BotPlayingStrategyFactory.createBotPlayingStrategy(difficulty);
    }

    public BotDifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BotDifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public Move getMove(Board board) {
        return strategy.makeMove(board, this);
    }
}
