public class Winner {
    private final Piece winningPiece;
    private final int[] winningCoordinates;

    public Winner(Piece winningPiece, int... winningCoordinates) {
        this.winningPiece = winningPiece;
        this.winningCoordinates = winningCoordinates;
    }

    public Piece getWinningPiece() {
        return winningPiece;
    }

    public int[] getWinningCoordinates() {
        return winningCoordinates;
    }
}
