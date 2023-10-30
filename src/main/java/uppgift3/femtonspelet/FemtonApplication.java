package uppgift3.femtonspelet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FemtonApplication extends Application {
    private static Stage stage;
        @Override
    public void start(Stage stage) throws IOException {
            FemtonApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(FemtonApplication.class.getResource("15SpelUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinWidth(216);
        stage.setMaxWidth(216);
        stage.setMinHeight(300);
        stage.setMaxHeight(300);
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