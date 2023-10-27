package uppgift3.femtonspelet;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FemtonApplication extends Application {

        @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FemtonApplication.class.getResource("15SpelUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 240);
        stage.setTitle("15-Spelet");
        stage.setScene(scene);
        stage.show();

            FemtonController controller = fxmlLoader.getController();
            controller.kopplaTextMedRektangel();
    }

    public static void main(String[] args) {
        launch();
    }
}