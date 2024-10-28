// HumanPlayer class representing a human player in the Tic-Tac-Toe game.
// This class extends the Player class and implements the move logic for a human player.
package com.assignment.tictactoe.service;

public class HumanPlayer extends Player {

    // Constructor to initialize the human player with the game board reference
    public HumanPlayer(BoardImpl board) {
        super(board);
    }

    // Implementation of the move method for a human player
    // Checks if the move is legal on the board at the given row and column
    // If legal, updates the board with the player's move (using Piece.X)
    @Override
    public void move(int row, int col) {
        if(board.isLegalMove(row, col)){
            board.updateMove(row, col, Piece.X);
        }
    }
}
