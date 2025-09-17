package com.rajeev.controllers;

import com.rajeev.models.Game;
import com.rajeev.models.GameState;
import com.rajeev.models.Player;
import com.rajeev.strategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        return Game
                .getBuilder()
                .setDimension(dimension)
                .setPlayers(players) //We can have singular adder functions instead of having a plural set function.
//                .addPlayer(players.get(0))
//                .addPlayer(players.get(1))
//                .addPlayer(players.get(2))
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void display(Game game) {
        game.getBoard().display();
    }

    public void makeMove(Game game) {
        try {
            game.makeMove();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public void undo(Game game) {
        game.undo();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
