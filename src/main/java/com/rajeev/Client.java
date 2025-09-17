package com.rajeev;

import com.rajeev.controllers.GameController;
import com.rajeev.models.*;
import com.rajeev.strategies.RowWinningStrategy;
import com.rajeev.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Hello world! Welcome to the Tic Tac Toe world!");
        // GameController should be a single object which should be independent of Games.
        GameController gameController = new GameController();
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(0, "Rajeev", new Symbol('X', "Green"), PlayerType.HUMAN));
        players.add(new HumanPlayer(1, "Alex", new Symbol('0', "Red"), PlayerType.HUMAN));
        players.add(new BotPlayer(2, "BotMan", new Symbol('Y', "Orange"), PlayerType.BOT, BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        Game game = gameController.startGame(3, players, winningStrategies);
        gameController.display(game);

        while(gameController.checkState(game).equals(GameState.RUNNING)) {
            gameController.makeMove(game);
            gameController.display(game);

            if(game.getCurrentPlayer().getPlayerType().equals(PlayerType.BOT)) {
                continue;
            }
            System.out.println("Do you want to undo [Y/N]?");
            String undoResponse = scanner.nextLine();

            if(undoResponse.equalsIgnoreCase("y")) {
                gameController.undo(game);
                gameController.display(game);
            }
        }

        if(gameController.checkState(game).equals(GameState.SUCCESS)) {
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        } else if(gameController.checkState(game).equals(GameState.DRAW)) {
            System.out.println("Game ends in a draw!");
        }
        gameController.display(game);
    }
}