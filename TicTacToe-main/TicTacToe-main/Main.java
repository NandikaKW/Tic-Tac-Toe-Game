import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BoardUI boardUI = new BoardUI(); // Create an instance of BoardUI
            boardUI.setVisible(true); // Set the UI to be visible
        });
    }
}
