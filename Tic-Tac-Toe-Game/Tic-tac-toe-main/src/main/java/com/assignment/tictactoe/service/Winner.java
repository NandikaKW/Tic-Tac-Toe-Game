// Winner class representing the winning state in a Tic-Tac-Toe game.
// It holds the winning piece and the coordinates (column, row) of the three winning positions on the game board.
package com.assignment.tictactoe.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class Winner {

    // The piece (X or O) that won the game
    private Piece winningPiece;

    // Coordinates of the first position in the winning line
    private int col1;
    private int row1;

    // Coordinates of the second position in the winning line
    private int col2;
    private int row2;

    // Coordinates of the third position in the winning line
    private int col3;
    private int row3;

    // Constructor to initialize the winning piece and the three winning positions
    public Winner(Piece winningPiece, int col1, int row1, int col2, int row2, int col3, int row3) {
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.col3 = col3;
        this.row3 = row3;
    }
}
