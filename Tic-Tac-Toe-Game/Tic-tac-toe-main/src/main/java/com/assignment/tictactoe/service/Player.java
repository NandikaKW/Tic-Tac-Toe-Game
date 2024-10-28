// Abstract Player class representing a player in the Tic-Tac-Toe game.
// Each player has access to the game board (BoardImpl) and must implement their own move logic.
package com.assignment.tictactoe.service;

abstract class Player {
    // Reference to the game board on which the player will make a move
    protected BoardImpl board;

    // Constructor to initialize the player with a reference to the game board
    public Player(BoardImpl board) {
        this.board = board;
    }

    // Abstract method for making a move on the board at the specified row and column
    // Each subclass must provide its own implementation of this move method
    public abstract void move(int row, int col);
}
