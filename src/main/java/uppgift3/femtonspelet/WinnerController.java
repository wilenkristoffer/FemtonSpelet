package uppgift3.femtonspelet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class WinnerController {
    public TextField nedskrivetNamn;
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

    private String timerTiden;

    @FXML
    public void initialize(String timerText) {
        this.timerTiden =timerText;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uppgift3/femtonspelet/highscore.txt"))) {
            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null && lineNumber <= 5) {

                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String datum = parts[0];
                    String namn = parts[1];
                    String tid = parts[2];


                    switch (lineNumber) {
                        case 1:
                            datum1.setText(datum);
                            namn1.setText(namn);
                            tid1.setText(tid);
                            break;
                        case 2:
                            datum2.setText(datum);
                            namn2.setText(namn);
                            tid2.setText(tid);
                            break;
                        case 3:
                            datum3.setText(datum);
                            namn3.setText(namn);
                            tid3.setText(tid);
                            break;
                        case 4:
                            datum4.setText(datum);
                            namn4.setText(namn);
                            tid4.setText(tid);
                            break;
                        case 5:
                            datum5.setText(datum);
                            namn5.setText(namn);
                            tid5.setText(tid);
                            break;
                    }

                    lineNumber++;
                }
            }
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa in filen!");
            throw new RuntimeException(e);
        }
    }

    public void clickedSpara() {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/uppgift3/femtonspelet/highscore.txt", true))) {

            bw.write(LocalDate.now()+ ", " + nedskrivetNamn.getText() + ", " + timerTiden);
            bw.newLine();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fel vid skrivning till fil!");
            throw new RuntimeException(e);
        }

}


}