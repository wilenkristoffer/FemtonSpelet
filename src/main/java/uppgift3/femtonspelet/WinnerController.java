package uppgift3.femtonspelet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WinnerController {
    public TextField nedskrivetNamn;
    public Label tiden;
    @FXML
    private Label datum1;
    @FXML
    public Label namn1;
    @FXML
    public Label tid1;
    @FXML
    public Label datum2;
    @FXML
    public Label namn2;
    @FXML
    public Label tid2;
    @FXML
    public Label datum3;
    @FXML
    public Label namn3;
    @FXML
    public Label tid3;
    @FXML
    public Label datum4;
    @FXML
    public Label namn4;
    @FXML
    public Label tid4;
    @FXML
    public Label datum5;
    @FXML
    public Label namn5;
    @FXML
    public Label tid5;
    @FXML
    private String timerTiden;

    @FXML
    public void initialize(String timerText) {
        this.timerTiden = timerText;


        tiden.setText(timerText);
    }


    public void clickedSpara() {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/uppgift3/femtonspelet/highscore.txt", true))) {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            String formattedDate = now.format(formatter);
            bw.write(formattedDate+ ", " + nedskrivetNamn.getText() + ", " + timerTiden);
            bw.newLine();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fel vid skrivning till fil!");
            throw new RuntimeException(e);
        }

}


}