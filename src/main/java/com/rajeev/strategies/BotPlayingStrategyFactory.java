package com.rajeev.strategies;

import com.rajeev.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy createBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        if(difficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        }
        return null;
    }
}
