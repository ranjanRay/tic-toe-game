package com.rajeev.models;

import com.rajeev.strategies.RowWinningStrategy;
import com.rajeev.strategies.Undoable;
import com.rajeev.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private int nextPlayerIndex;
    private GameState gameState;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;
    private Player winner;

    private Game(Builder builder) {
        this.board = new Board(builder.dimension);
        this.players = builder.players;
        this.winningStrategies = builder.winningStrategies;
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.gameState = GameState.RUNNING;
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        int index = (nextPlayerIndex - 1 + players.size()) % players.size();
        return players.get(index);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void display() {
        board.display();
    }

    public void undo() {
        if(moves.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        Move lastMove = moves.get(moves.size() - 1);
        for(WinningStrategy strategy : winningStrategies) {
            if(strategy instanceof Undoable) {
                ((Undoable) strategy).undoMoveSideEffects(lastMove);
            }
        }
        Player lastPlayer = lastMove.getPlayer();
        Cell lastCell = lastMove.getCell();
        lastCell.setState(CellState.EMPTY);
        lastCell.setSymbol(null);
        nextPlayerIndex = lastPlayer.getId();
        moves.remove(lastMove);
        if(gameState.equals(GameState.SUCCESS)) {
            setGameState(GameState.RUNNING);
            setWinner(null);
        }
    }

    public boolean checkWinner(Move move) {
        for(WinningStrategy winningStrategy : winningStrategies) {
            if(winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public void makeMove() {
        // 1. Validate move.

        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("It's "+ currentPlayer.getName() + "'s move.");
        Move move = currentPlayer.getMove(board);
        if(!validateMove(move)) {
            throw new RuntimeException("Invalid move");
        }

        // 2. Update cell with the current move.
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cell = board.getGrid().get(row).get(col);
        cell.setState(CellState.OCCUPIED);
        cell.setSymbol(currentPlayer.getSymbol());

        // 3. update list of moves.

        move.setCell(cell);
        moves.add(move);

        // 4. Checkwinner.
        if(checkWinner(move)) {
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);
        } else if (moves.size() == board.getSize() * board.getSize()) {
            setWinner(null);
            setGameState(GameState.DRAW);
        }

        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
    }

    private boolean validateMove(Move move) {
        int r = move.getCell().getRow();
        int c = move.getCell().getCol();

        if(r < 0 || c < 0 || r >= board.getGrid().size() || c >= board.getGrid().size()) {
            return false;
        }

        return board.getGrid().get(r).get(c).getState() == CellState.EMPTY;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        private Builder() {
            this.players = new ArrayList<>();
        }
        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public void validate() {
//            1. At max 1 bot in the game.
//            2. Players size = dimension - 1
//            3. Every player must have a separate symbol.

            int bots = 0;
            for(Player player : players) {
                if(player.getPlayerType() == PlayerType.BOT) { bots++; }
                if(bots > 1) {
                    throw new RuntimeException("Too many bots.");
                }
            }

            if(players.size() != dimension) {
                throw new RuntimeException("Invalid players count.");
            }

            Set<Symbol> uniqueSymbols = new HashSet<>();
            for(Player player : players) {
                if(uniqueSymbols.contains(player.getSymbol())) {
                    throw new RuntimeException("Duplicate symbol.");
                }
                uniqueSymbols.add(player.getSymbol());
            }
        }

        public Game build() {
            validate();
            return new Game(this);
        }
    }
}
