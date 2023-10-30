package uppgift3.femtonspelet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
    private Color currentColor;

    public void initialize() {

        currentColor = Color.DODGERBLUE;

        initializePuzzle(currentColor);
        shufflePuzzle();
        updatePuzzleUI();
    }

    private void initializePuzzle(Color color) {
        puzzleLayout = new Group[4][4];

        int number = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (row == 3 && col == 3) {
                    puzzleLayout[row][col] = null;
                } else {

                    Group puzzlePiece = createPuzzlePiece(number, color);
                    puzzleLayout[row][col] = puzzlePiece;
                    gridPane.add(puzzlePiece, col, row);
                    number++;
                }
            }
        }
        emptyRow = 3;
        emptyCol = 3;

    }


    private Group createPuzzlePiece(int number, Color color) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(color);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);

        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1.5);

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

    public void nyttSpel(ActionEvent actionEvent) {
        shufflePuzzle();
        updatePuzzleUI();

    }


    private boolean isPuzzleSolved() {
        int expectedNumber = 1;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Group puzzlePiece = puzzleLayout[row][col];
                if (puzzlePiece != null) {
                    Text text = (Text) puzzlePiece.getChildren().get(1);
                    int textValue = Integer.parseInt(text.getText());
                    if (textValue != expectedNumber) {
                        return false;
                    }
                } else {
                    if (row != 3 || col != 3) {
                        return false;
                    }
                }
                expectedNumber++;
            }
        }

        return true;
    }

    public void handleSetBlue() {
        currentColor = Color.DODGERBLUE;
        changePuzzleColor(currentColor);
    }

    public void handleSetOrange() {
        currentColor = Color.ORANGE;
        changePuzzleColor(currentColor);
    }

    public void handleSetRed() {
        currentColor = Color.RED;
        changePuzzleColor(currentColor);
    }

    public void handleSetGreen() {
        currentColor = Color.GREEN;
        changePuzzleColor(currentColor);
    }
    private void changePuzzleColor(Color color) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (puzzleLayout[row][col] != null) {
                    Rectangle rectangle = (Rectangle) puzzleLayout[row][col].getChildren().get(0);
                    rectangle.setFill(color);
                }
            }
        }
    }
}