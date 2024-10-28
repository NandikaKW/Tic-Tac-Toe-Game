package com.assignment.tictactoe.service; // Specifies the package for the BoardUi interface

// Defines the BoardUi interface
public interface BoardUi {
    // Method to update the game board at the specified row and column with the given piece
    void update(int row, int col, Piece piece);

    // Method to notify about the winner of the game
    void NotifyWinner(Piece winner);
}
