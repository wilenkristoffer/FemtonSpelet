package uppgift3.femtonspelet;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class FemtonController {
    @FXML
    private GridPane gridPane;

    private Group[][] puzzleLayout;
    private int emptyRow;
    private int emptyCol;

    public void initialize() {
        puzzleLayout = new Group[4][4];
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
                    Group puzzlePiece = createPuzzlePiece(number);
                    puzzleLayout[row][col] = puzzlePiece;
                    gridPane.add(puzzlePiece, col, row);
                    number++;
                }
            }
        }
        emptyRow = 3;
        emptyCol = 3;
    }

    private Group createPuzzlePiece(int number) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);

        Text text = new Text(String.valueOf(number));
        text.setFont(Font.font(16));
        double textWidth = text.getLayoutBounds().getWidth();
        double textHeight = text.getLayoutBounds().getHeight();

        double x = (rectangle.getWidth() - textWidth) / 2;
        double y = (rectangle.getHeight() - textHeight);

        text.setX(x);
        text.setY(y);

        Group group = new Group(rectangle, text);

        group.setOnMouseClicked(event -> handleRectangleClick(group));

        return group;
    }

    private void shufflePuzzle() {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            int randomMove = random.nextInt(4);
            int newRow = emptyRow;
            int newCol = emptyCol;

            switch (randomMove) {
                case 0:
                    newRow = Math.min(emptyRow + 1, 3);
                    break;
                case 1:
                    newRow = Math.max(emptyRow - 1, 0);
                    break;
                case 2:
                    newCol = Math.min(emptyCol + 1, 3);
                    break;
                case 3:
                    newCol = Math.max(emptyCol - 1, 0);
                    break;
            }

            if ((newRow != emptyRow || newCol != emptyCol)) {
                Group temp = puzzleLayout[newRow][newCol];
                puzzleLayout[newRow][newCol] = null;
                puzzleLayout[emptyRow][emptyCol] = temp;
                emptyRow = newRow;
                emptyCol = newCol;
            }
        }
    }

    private void handleRectangleClick(Group clickedGroup) {
        int clickedRow = GridPane.getRowIndex(clickedGroup);
        int clickedCol = GridPane.getColumnIndex(clickedGroup);

        if ((clickedRow == emptyRow && Math.abs(clickedCol - emptyCol) == 1)
                || (clickedCol == emptyCol && Math.abs(clickedRow - emptyRow) == 1)) {
            puzzleLayout[emptyRow][emptyCol] = clickedGroup;
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
                Group puzzlePiece = puzzleLayout[row][col];
                if (puzzlePiece != null) {
                    gridPane.add(puzzlePiece, col, row);
                }
            }
        }
    }
}

