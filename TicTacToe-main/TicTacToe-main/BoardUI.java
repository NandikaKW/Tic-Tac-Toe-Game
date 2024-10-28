import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardUI extends JFrame implements ActionListener {
    private final BoardImpl board;
    private final JButton[][] buttons;
    private final HumanPlayer humanPlayer;
    private final AIPlayer aiPlayer;
    private boolean isHumanTurn = true; // Flag to check whose turn it is

    public BoardUI() {
        board = new BoardImpl();
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AIPlayer(board);
        buttons = new JButton[3][3];

        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeButtons();
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setActionCommand(row + "," + col);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int row = Integer.parseInt(command.split(",")[0]);
        int col = Integer.parseInt(command.split(",")[1]);

        if (isHumanTurn) {
            humanPlayer.move(row, col);
            buttons[row][col].setText("X");
            // Check for a winner after the human player's move
            Winner winner = board.checkWinner();
            if (winner != null) {
                JOptionPane.showMessageDialog(this, "Winner: " + winner.getWinningPiece());
                resetGame();
                return; // Exit after resetting
            }
        } else {
            // The AI makes its move
            aiPlayer.makeMove();
            updateBoardUI();
            // Check for a winner after the AI's move
            Winner winner = board.checkWinner();
            if (winner != null) {
                JOptionPane.showMessageDialog(this, "Winner: " + winner.getWinningPiece());
                resetGame();
                return; // Exit after resetting
            }
        }

        // Switch turns after both moves
        isHumanTurn = !isHumanTurn;
    }


    private void updateBoardUI() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.pieces[row][col] != Piece.EMPTY) {
                    buttons[row][col].setText(board.pieces[row][col].toString());
                }
            }
        }
    }

    private void resetGame() {
        board.initializeBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        isHumanTurn = true; // Reset to human's turn at the start of a new game
    }
}
