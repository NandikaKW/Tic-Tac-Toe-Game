// BoardImpl class implements the Board interface and provides the concrete implementation
// for managing the Tic-Tac-Toe game board. It handles initializing, updating, and checking
// the state of the board, along with determining if there is a winner or if the board is full.
package com.assignment.tictactoe.service;

public class BoardImpl implements Board {

    // 2D array of Piece representing the 3x3 Tic-Tac-Toe board.
    private Piece[][] pieces = new Piece[3][3];

    // Constructor that initializes the board with empty pieces.
    public BoardImpl() {
        initializeBoard();
    }

    // Returns the current state of the board (2D array of Piece).
    public Piece[][] getPieces() {
        return pieces;
    }

    // Initializes the board by setting all cells to EMPTY.
    @Override
    public void initializeBoard() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    // Checks if the move at the specified row and column is legal (i.e., the cell is EMPTY).
    @Override
    public boolean isLegalMove(int row, int col) {
        return pieces[row][col] == Piece.EMPTY;
    }

    // Updates the board at the specified row and column with the given piece (X or O).
    @Override
    public void updateMove(int row, int col, Piece piece) {
        pieces[row][col] = piece;
    }

    // Checks if there is a winner by checking rows, columns, and diagonals.
    // If a winning combination is found, returns a Winner object; otherwise, returns null.
    @Override
    public Winner checkWinner() {
        // Check rows and columns for a winning combination.
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] == pieces[i][1] && pieces[i][0] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) {
                return new Winner(pieces[i][0], i, 0, i, 1, i, 2);
            }
            if (pieces[0][i] == pieces[1][i] && pieces[0][i] == pieces[2][i] && pieces[0][i] != Piece.EMPTY) {
                return new Winner(pieces[0][i], 0, i, 1, i, 2, i);
            }
        }

        // Check diagonals for a winning combination.
        if (pieces[0][0] == pieces[1][1] && pieces[0][0] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) {
            return new Winner(pieces[0][0], 0, 0, 1, 1, 2, 2);
        }
        if (pieces[0][2] == pieces[1][1] && pieces[0][2] == pieces[2][0] && pieces[0][2] != Piece.EMPTY) {
            return new Winner(pieces[0][2], 0, 2, 1, 1, 2, 0);
        }

        // No winner found, return null.
        return null;
    }

    // Prints the current state of the board to the console.
    @Override
    public void printBoard() {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                System.out.print(piece + " ");
            }
            System.out.println();
        }
    }

    // Checks if the board is full (i.e., there are no EMPTY cells).
    public boolean isBoardFull() {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
