import java.util.Random;

public class AIPlayer extends Player {
    private final Random random = new Random();

    public AIPlayer(BoardImpl board) {
        super(board);
    }

    @Override
    public void move(int row, int col) {
        // The AI player should not have a move method with parameters
        // Instead, create a method to select a random legal move.
        while (true) {
            int randomRow = random.nextInt(3);
            int randomCol = random.nextInt(3);
            if (board.isLegalMove(randomRow, randomCol)) {
                board.updateMove(randomRow, randomCol, Piece.O);
                break;
            }
        }
    }

    // New method to make the AI move without parameters
    public void makeMove() {
        while (true) {
            int randomRow = random.nextInt(3);
            int randomCol = random.nextInt(3);
            if (board.isLegalMove(randomRow, randomCol)) {
                board.updateMove(randomRow, randomCol, Piece.O);
                break;
            }
        }
    }
}
