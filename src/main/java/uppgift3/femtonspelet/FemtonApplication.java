package uppgift3.femtonspelet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FemtonApplication extends Application {

        @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FemtonApplication.class.getResource("15SpelUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 260);
        stage.setTitle("15-Spelet");
        stage.setScene(scene);
        stage.show();

            FemtonController controller = fxmlLoader.getController();
            controller.initialize();
    }

    public static void main(String[] args) {
        launch();
    }
}