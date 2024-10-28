// BoardController class is responsible for managing the game logic and user interface of the Tic-Tac-Toe game.
// It acts as the bridge between the service layer (game logic) and the UI layer (JavaFX).
// This class handles player moves (both human and AI), updates the UI, and checks the game status.

package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardController implements BoardUi {

    private BoardImpl board;  // The game board instance
    private AiPlayer ai;      // The AI player instance
    private HumanPlayer human; // The human player instance
//fahim
    @FXML
    private GridPane MainGrid; // The main gridpane that contains the game buttons

    // Constructor initializes the game board, AI player, and human player
    public BoardController() {
        board = new BoardImpl();
        ai = new AiPlayer(board);
        human = new HumanPlayer(board);
    }

    // Handles button clicks in the UI, which represent player moves.
    // It first checks if the button is clickable (valid move), then updates the board for the human move,
    // updates the UI, checks for game status, and finally lets the AI make a move if the game isn't over.
    @FXML
    void handleButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();

        if (!isButtonClickable(button)) return;

        // Extract row and column from the button ID
        int row = getRowFromButtonId(button.getId());
        int col = getColFromButtonId(button.getId());

        // Process the human move
        human.move(row, col);
        updateUi();

        // Check if the game is over
        if (checkGameStatus()) return;

        // AI makes its move
        ai.findBestMove();
        updateUi();
        checkGameStatus();
    }

    // Validates if the button clicked is still clickable (i.e., hasn't been clicked before).
    private boolean isButtonClickable(Button button) {
        if (!button.getText().isEmpty()) {
            showSimpleAlert("This button is already clicked! Please select another button.");
            return false;
        }
        return true;
    }

    // Extracts the row from the button ID (e.g., "bt00" -> row 0)
    private int getRowFromButtonId(String buttonId) {
        return Integer.parseInt(buttonId.substring(2, 3));
    }

    // Extracts the column from the button ID (e.g., "bt00" -> col 0)
    private int getColFromButtonId(String buttonId) {
        return Integer.parseInt(buttonId.substring(3, 4));
    }

    // Checks the status of the game: if there's a winner or if the board is full (a tie).
    // Displays appropriate messages and returns true if the game has ended.
    private boolean checkGameStatus() {
        Winner winner = board.checkWinner();
        if (winner != null) {
            NotifyWinner(winner.getWinningPiece());
            return true;
        } else if (board.isBoardFull()) {
            showAlert("Game is a tie!");  // Updated tie message
            return true;
        }
        return false;
    }

    // Updates the entire UI to reflect the current state of the board.
    // It loops through each piece on the board and updates the corresponding button in the UI.
    public void updateUi() {
        for (int row = 0; row < board.getPieces().length; row++) {
            for (int col = 0; col < board.getPieces()[row].length; col++) {
                update(row, col, board.getPieces()[row][col]);
            }
        }
    }

    // Updates a specific button in the UI to reflect the current piece (X, O, or empty) at the given row and column.
    @Override
    public void update(int row, int col, Piece piece) {
        String buttonId = "#bt" + row + col;
        Button button = (Button) MainGrid.lookup(buttonId);
        if (button != null) {
            button.setText(piece == Piece.X ? "X" : piece == Piece.O ? "O" : "");
        }
    }

    // Notifies the user about the winner by displaying an alert with the winning message.
    @Override
    public void NotifyWinner(Piece winner) {
        if (winner == Piece.X) {
            showAlert("Human (X) wins!");  // Updated human win message
        } else if (winner == Piece.O) {
            showAlert("AI (O) wins!");  // Updated AI win message
        }
    }

    // Displays an informational alert to notify the user of game events such as win, loss, or tie.
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setOnCloseRequest(event -> resetGame());
        alert.showAndWait();
    }

    // Resets the game by re-initializing the board and updating the UI for a fresh start.
    private void resetGame() {
        board.initializeBoard();
        updateUi();
    }

    // Shows a simple informational alert with a custom message, typically for invalid moves.
    private void showSimpleAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}
