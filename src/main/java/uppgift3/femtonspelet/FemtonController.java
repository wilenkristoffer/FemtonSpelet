package uppgift3.femtonspelet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class FemtonController {
    @FXML
    public Label timerLabel;
    @FXML
    private GridPane gridPane;
    private Group[][] puzzleLayout;
    private int emptyRow;
    private int emptyCol;
    @FXML
    private VBox mainVbox;
    private int elapsedSeconds = 0;
    private Timeline timerTimeline;



    public void initialize() {
        puzzleLayout = new Group[4][4];
        initializePuzzle();
        shufflePuzzle();
        updatePuzzleUI();


        if (timerTimeline != null) {
            timerTimeline.stop();
        }
        //Vid nytt spel så resettar vi timern
        elapsedSeconds = 0;

        timerTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
        timerTimeline.play();
    }

    private void updateTimer() {
        elapsedSeconds++;
        int minutes = elapsedSeconds / 60;
        int seconds = elapsedSeconds % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(timeString);
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
        rectangle.setFill(Color.web("#FFA500"));
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);


        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
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

        group.setOnMouseClicked(event -> {
            try {
                handleRectangleClick(group);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

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

    private void handleRectangleClick(Group clickedGroup) throws IOException {
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
        if (isPuzzleSolved()) {
            showWinnerDialog();
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
        initialize();

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
@FXML
    public void showWinnerDialog() {
    timerTimeline.stop();

    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.initOwner(mainVbox.getScene().getWindow());
    dialog.setTitle("High-score tavlan");

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Winner.fxml"));
        Parent root = loader.load();
        dialog.getDialogPane().setContent(root);
        WinnerController wc = loader.getController();
        wc.initialize(timerLabel.getText());

    ButtonType spara = new ButtonType("Spara", ButtonBar.ButtonData.OK_DONE);
    ButtonType stang = new ButtonType("Stäng", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(spara, stang);

    Optional<ButtonType> result = dialog.showAndWait();

    if (result.isPresent()) {
        if (result.get() == spara) {
            wc.clickedSpara();
        } else if (result.get() == stang) {
            dialog.close();
        }
    }
    } catch (IOException e) {
        System.out.println("Fel vid laddning av vinnar-dialogen!");
        throw new RuntimeException(e);
    }
}

    @FXML
    private void handleSolveButtonAction(MouseEvent event) {

        resetPuzzleToSolvedState();
    }

    private void resetPuzzleToSolvedState() {
        int number = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (row == 3 && col == 3) {
                    puzzleLayout[row][col] = null;
                } else {
                    Group puzzlePiece = createPuzzlePiece(number);
                    puzzleLayout[row][col] = puzzlePiece;
                    number++;
                }
            }
        }

        emptyRow = 3;
        emptyCol = 3;

        updatePuzzleUI();

    }
}