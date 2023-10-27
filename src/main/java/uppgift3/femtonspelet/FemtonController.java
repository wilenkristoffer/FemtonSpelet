package uppgift3.femtonspelet;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
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


    public void kopplaTextMedRektangel() {


        text1.xProperty().bind(rec1.layoutXProperty());
        text1.yProperty().bind(rec1.layoutYProperty());
        text2.xProperty().bind(rec2.layoutXProperty().add(10));
        text2.yProperty().bind(rec2.layoutYProperty().add(10));

        rec1.setOnMousePressed(this::handleMousePressed);
        rec1.setOnMouseDragged(this::handleMouseDragged);
        rec2.setOnMousePressed(this::handleMousePressed);
        rec2.setOnMouseDragged(this::handleMouseDragged);

        text3.xProperty().bind(rec3.layoutXProperty());
        text3.yProperty().bind(rec3.layoutYProperty());
        text4.xProperty().bind(rec4.layoutXProperty());
        text4.yProperty().bind(rec4.layoutYProperty());

        rec3.setOnMousePressed(this::handleMousePressed);
        rec3.setOnMouseDragged(this::handleMouseDragged);
        rec4.setOnMousePressed(this::handleMousePressed);
        rec4.setOnMouseDragged(this::handleMouseDragged);


        setDraggable(rec1);
        setDraggable(rec2);

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