package uppgift3.femtonspelet;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FemtonController {

    private double xOffset;
    private double yOffset;
    private final double gridSize = 50.0;
    @FXML
    private Rectangle emptyRec;
    @FXML
    private Rectangle rec1;
    @FXML
    private Text text1;
    @FXML
    private Rectangle rec2;
    @FXML
    private Text text2;
    @FXML
    private Rectangle rec3;
    @FXML
    private Text text3;
    @FXML
    private Rectangle rec4;
    @FXML
    private Text text4;
    @FXML
    private Rectangle rec5;
    @FXML
    private Text text5;
    @FXML
    private Rectangle rec6;
    @FXML
    private Text text6;
    @FXML
    private Rectangle rec7;
    @FXML
    private Text text7;
    @FXML
    private Rectangle rec8;
    @FXML
    private Text text8;
    @FXML
    private Rectangle rec9;
    @FXML
    private Text text9;
    @FXML
    private Rectangle rec10;
    @FXML
    private Text text10;
    @FXML
    private Rectangle rec11;
    @FXML
    private Text text11;
    @FXML
    private Rectangle rec12;
    @FXML
    private Text text12;
    @FXML
    private Rectangle rec13;
    @FXML
    private Text text13;
    @FXML
    private Rectangle rec14;
    @FXML
    private Text text14;
    @FXML
    private Rectangle rec15;
    @FXML
    private Text text15;


    public void kopplaTextMedRektangel() {


        VBox root = (VBox) text1.getScene().getRoot();

        for (int i = 1; i <= 15; i++) {
            Rectangle rec = (Rectangle) root.lookup("#rec" + i);
            Text text = (Text) root.lookup("#text" + i);

            text.xProperty().bind(rec.layoutXProperty());
            text.yProperty().bind(rec.layoutYProperty());

            setDraggable(rec);


        }
    }

    private void setDraggable(Rectangle rectangle) {
        rectangle.setOnMousePressed(this::handleMousePressed);
        rectangle.setOnMouseDragged(this::handleMouseDragged);
        rectangle.setOnMouseReleased(this::handleMouseReleased);
    }


    private void handleMousePressed(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getSource();
        xOffset = event.getSceneX() - rectangle.getLayoutX();
        yOffset = event.getSceneY() - rectangle.getLayoutY();
    }

    private void handleMouseDragged(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getSource();
        double newX = event.getSceneX() - xOffset;
        double newY = event.getSceneY() - yOffset;

        // Calculate the snapped position
        double snappedX = Math.round(newX / gridSize) * gridSize;
        double snappedY = Math.round(newY / gridSize) * gridSize;

        // Ensure the rectangle won't move outside the 4x4 space
        if (snappedX >= 0 && snappedX <= 150 && snappedY >= 0 && snappedY <= 150) {
            // Calculate the row and column of the target position
            int targetRow = (int) (snappedY / gridSize);
            int targetCol = (int) (snappedX / gridSize);

            // Calculate the row and column of the current position
            int currentRow = (int) (rectangle.getLayoutY() / gridSize);
            int currentCol = (int) (rectangle.getLayoutX() / gridSize);

            // Check if the target location is adjacent to the current location
            boolean isAdjacent = (Math.abs(targetRow - currentRow) == 1 && targetCol == currentCol)
                    || (Math.abs(targetCol - currentCol) == 1 && targetRow == currentRow);

            if (isAdjacent) {
                rectangle.setLayoutX(snappedX);
                rectangle.setLayoutY(snappedY);
            }
        }
    }
        private void handleMouseReleased (MouseEvent event){
            Rectangle sourceRectangle = (Rectangle) event.getSource();

            // Ensure that the empty rectangle is part of the GridPane
            if (emptyRec.getParent() instanceof GridPane) {
                // Calculate the row and column indices of the source and empty rectangles
                int sourceRow = GridPane.getRowIndex(sourceRectangle);
                int sourceColumn = GridPane.getColumnIndex(sourceRectangle);
                int emptyRow = GridPane.getRowIndex(emptyRec);
                int emptyColumn = GridPane.getColumnIndex(emptyRec);

                // Check if the source rectangle is adjacent to the empty rectangle
                if ((Math.abs(sourceRow - emptyRow) == 1 && sourceColumn == emptyColumn) ||
                        (Math.abs(sourceColumn - emptyColumn) == 1 && sourceRow == emptyRow)) {
                    // Swap the positions of the source rectangle and the empty rectangle
                    emptyRec.setLayoutX(sourceRectangle.getLayoutX());
                    emptyRec.setLayoutY(sourceRectangle.getLayoutY());
                    GridPane.setRowIndex(emptyRec, sourceRow);
                    GridPane.setColumnIndex(emptyRec, sourceColumn);

                    sourceRectangle.setLayoutX(emptyRec.getLayoutX());
                    sourceRectangle.setLayoutY(emptyRec.getLayoutY());
                    GridPane.setRowIndex(sourceRectangle, emptyRow);
                    GridPane.setColumnIndex(sourceRectangle, emptyColumn);
                }
            }
        }
    }
