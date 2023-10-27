package uppgift3.femtonspelet;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FemtonController {

    private double xOffset;
    private double yOffset;
    private final double gridSize = 50.0;

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


        double snappedX = Math.round(newX / gridSize) * gridSize;
        double snappedY = Math.round(newY / gridSize) * gridSize;


        rectangle.setLayoutX(snappedX);
        rectangle.setLayoutY(snappedY);
    }

    private void handleMouseReleased(MouseEvent event) {

    }
}