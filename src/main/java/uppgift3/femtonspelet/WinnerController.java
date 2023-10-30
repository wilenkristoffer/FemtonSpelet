package uppgift3.femtonspelet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WinnerController {
    private String filNamn = "src/main/java/uppgift3/femtonspelet/highscore.txt";
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
        //Får in tiden som användaren klarade spelet på, sparas till filen.
        this.timerTiden = timerText;
        HighScoreModel hsm = new HighScoreModel();
        hsm.loadHighScoresFromFile(filNamn);
        //Får listan med sorterade high-scores
        List<HighScoreModel.HighScoreEntry> highScores = hsm.getHighScores();
        //Kollar igenom high-scores listan ifall det finns text att skriva ut.
        //Ifall listan är tom/ofullständig visas default texten för dialogrutan.
        if (!highScores.isEmpty()) {
            HighScoreModel.HighScoreEntry entry1 = highScores.get(0);
            datum1.setText(entry1.getDatum());
            namn1.setText(entry1.getNamn());
            tid1.setText(entry1.getTid());
        }

        if (highScores.size() >= 2) {
            HighScoreModel.HighScoreEntry entry2 = highScores.get(1);
            datum2.setText(entry2.getDatum());
            namn2.setText(entry2.getNamn());
            tid2.setText(entry2.getTid());
        }

        if (highScores.size() >= 3) {
            HighScoreModel.HighScoreEntry entry3 = highScores.get(2);
            datum3.setText(entry3.getDatum());
            namn3.setText(entry3.getNamn());
            tid3.setText(entry3.getTid());
        }

        if (highScores.size() >= 4) {
            HighScoreModel.HighScoreEntry entry4 = highScores.get(3);
            datum4.setText(entry4.getDatum());
            namn4.setText(entry4.getNamn());
            tid4.setText(entry4.getTid());
        }

        if (highScores.size() >= 5) {
            HighScoreModel.HighScoreEntry entry5 = highScores.get(4);
            datum5.setText(entry5.getDatum());
            namn5.setText(entry5.getNamn());
            tid5.setText(entry5.getTid());
        }
        //Texten för tiden som användaren klarade spelet på, visas till användaren
        tiden.setText(timerText);
    }
        //Metoden som kallas när man klickar på spara, sparar ner data till filen.
    public void clickedSpara() {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filNamn, true))) {
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