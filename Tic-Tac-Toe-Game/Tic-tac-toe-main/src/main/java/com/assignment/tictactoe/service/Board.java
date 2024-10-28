// Board interface defines the essential operations for managing a Tic-Tac-Toe board.
// It includes methods for initializing the board, checking valid moves, updating the board,
// determining if there's a winner, and printing the board state.
package com.assignment.tictactoe.service;

public interface Board {

    // Initializes the board by setting all positions to an initial state (e.g., empty).
    void initializeBoard();

    // Checks if the move to the specified row and column is legal (i.e., the position is not occupied).
    boolean isLegalMove(int row, int col);

    // Updates the board by placing the specified piece (X or O) at the given row and column.
    void updateMove(int row, int col, Piece piece);

    // Checks the current state of the board to determine if there is a winner.
    // Returns a Winner object if a winning condition is met, or null if no winner exists.
    Winner checkWinner();

    // Prints the current state of the board to the console.
    void printBoard();
}
