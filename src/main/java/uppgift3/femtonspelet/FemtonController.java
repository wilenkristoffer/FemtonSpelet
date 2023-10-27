package uppgift3.femtonspelet;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class FemtonController {

    @FXML
    private GridPane gridPane;

    private Rectangle[][] puzzleLayout;
    private int emptyRow;
    private int emptyCol;

    public void initialize() {
        puzzleLayout = new Rectangle[4][4];
        initializePuzzle();
        shufflePuzzle();
        updatePuzzleUI();
    }

    private void initializePuzzle() {
        int number = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (row == 3 && col == 3) {
                    puzzleLayout[row][col] = null;
                } else {
                    Rectangle rectangle = createPuzzlePiece(number);
                    puzzleLayout[row][col] = rectangle;
                    gridPane.add(rectangle, col, row);
                    number++;
                }
            }
        }
        emptyRow = 3;
        emptyCol = 3;
    }

    private Rectangle createPuzzlePiece(int number) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);

        Text text = new Text(String.valueOf(number));
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setTextOrigin(javafx.geometry.VPos.TOP);
        rectangle.setUserData(text);

        rectangle.setOnMouseClicked(event -> handleRectangleClick(rectangle));

        return rectangle;
    }

    private void shufflePuzzle() {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            int randomMove = random.nextInt(4);
            int newRow = emptyRow;
            int newCol = emptyCol;

            switch (randomMove) {
                case 0: // Up
                    newRow = Math.min(emptyRow + 1, 3);
                    break;
                case 1: // Down
                    newRow = Math.max(emptyRow - 1, 0);
                    break;
                case 2: // Left
                    newCol = Math.min(emptyCol + 1, 3);
                    break;
                case 3: // Right
                    newCol = Math.max(emptyCol - 1, 0);
                    break;
            }

            if ((newRow != emptyRow || newCol != emptyCol)) {
                Rectangle temp = puzzleLayout[newRow][newCol];
                puzzleLayout[newRow][newCol] = null;
                puzzleLayout[emptyRow][emptyCol] = temp;
                emptyRow = newRow;
                emptyCol = newCol;
            }
        }
    }

    private void handleRectangleClick(Rectangle clickedRectangle) {
        int clickedRow = GridPane.getRowIndex(clickedRectangle);
        int clickedCol = GridPane.getColumnIndex(clickedRectangle);

        if ((clickedRow == emptyRow && Math.abs(clickedCol - emptyCol) == 1)
                || (clickedCol == emptyCol && Math.abs(clickedRow - emptyRow) == 1)) {
            puzzleLayout[emptyRow][emptyCol] = clickedRectangle;
            puzzleLayout[clickedRow][clickedCol] = null;
            emptyRow = clickedRow;
            emptyCol = clickedCol;

            updatePuzzleUI();
        }
    }

    private void updatePuzzleUI() {
        gridPane.getChildren().clear();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Rectangle rectangle = puzzleLayout[row][col];
                if (rectangle != null) {
                    gridPane.add(rectangle, col, row);
                }
            }
        }
    }
}