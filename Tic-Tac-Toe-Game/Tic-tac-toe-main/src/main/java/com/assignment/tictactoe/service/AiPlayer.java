// AiPlayer class represents an AI-controlled player in the Tic-Tac-Toe game.
// This class extends the abstract Player class and implements the logic for making
// moves based on the minimax algorithm to play optimally.
package com.assignment.tictactoe.service;

public class AiPlayer extends Player {

    // Constructor initializes the AI player with the provided board.
    public AiPlayer(BoardImpl board) {
        super(board);
    }

    // Overrides the move method from the Player class to place an 'O' piece on the board.
    // The move is only made if it is legal.
    @Override
    public void move(int row, int col) {
        if (board.isLegalMove(row, col)) {
            board.updateMove(row, col, Piece.O);
        }
    }

    // Finds and makes the best move for the AI by evaluating all possible moves using the minimax algorithm.
    // It simulates the moves and selects the one with the highest score.
    public void findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        // Loop through all cells to evaluate the best possible move
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getPieces()[row][col] == Piece.EMPTY) {
                    // Simulate AI move
                    board.updateMove(row, col, Piece.O);

                    // Evaluate the move using the minimax algorithm
                    int score = minimax(false); // False because it's now the human's turn

                    // Undo the move
                    board.updateMove(row, col, Piece.EMPTY);

                    // Track the best score and corresponding move
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        // Make the best move if a valid one was found
        if (bestRow != -1 && bestCol != -1) {
            move(bestRow, bestCol);
        }
    }

    // The minimax algorithm evaluates the best possible outcome for both players.
    // isMaximizing is true when it's the AI's turn to maximize its score.
    private int minimax(boolean isMaximizing) {
        // Check for a winner or if the board is full, and return the score accordingly
        Winner winner = board.checkWinner();
        if (winner != null) {
            if (winner.getWinningPiece() == Piece.O) {
                return 10; // AI wins
            } else if (winner.getWinningPiece() == Piece.X) {
                return -10; // Human wins
            }
        }

        // If the board is full and there's no winner, it's a tie
        if (board.isBoardFull()) {
            return 0;
        }

        // Maximizing for AI (Piece O)
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.getPieces()[row][col] == Piece.EMPTY) {
                        // Simulate AI move
                        board.updateMove(row, col, Piece.O);

                        // Recursively call minimax for the human's turn
                        int score = minimax(false);

                        // Undo the move
                        board.updateMove(row, col, Piece.EMPTY);

                        // Track the highest score
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else { // Minimizing for Human (Piece X)
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.getPieces()[row][col] == Piece.EMPTY) {
                        // Simulate Human move
                        board.updateMove(row, col, Piece.X);

                        // Recursively call minimax for the AI's turn
                        int score = minimax(true);

                        // Undo the move
                        board.updateMove(row, col, Piece.EMPTY);

                        // Track the lowest score
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }
}
