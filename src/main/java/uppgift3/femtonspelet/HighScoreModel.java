package uppgift3.femtonspelet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoreModel {
    private List<HighScoreEntry> highScores;

    public HighScoreModel() {
        highScores = new ArrayList<>();
    }

    public void addHighScore(String datum, String namn, String tid) {
        highScores.add(new HighScoreEntry(datum, namn, tid));
        sortHighScores();
    }

    public List<HighScoreEntry> getHighScores() {
        return highScores;
    }

    public void sortHighScores() {
        Collections.sort(highScores, Comparator.comparing(HighScoreEntry::getTid));
    }

    public void loadHighScoresFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uppgift3/femtonspelet/highscore.txt"))) {
            String line;
            int lineCounter = 1;

            while ((line = br.readLine()) != null && lineCounter <= 5) {
                String[] parts = line.split(", ");
                String datum = parts[0];
                String namn = parts[1];
                String tid = parts[2];
                if (lineCounter == 1) {
                    datum1.setText(datum);
                    namn1.setText(namn);
                    tid1.setText(tid);
                } else if (lineCounter == 2) {
                    datum2.setText(datum);
                    namn2.setText(namn);
                    tid2.setText(tid);
                } else if (lineCounter == 3) {
                    datum3.setText(datum);
                    namn3.setText(namn);
                    tid3.setText(tid);
                } else if (lineCounter == 4) {
                    datum4.setText(datum);
                    namn4.setText(namn);
                    tid4.setText(tid);
                } else if (lineCounter == 5) {
                    datum5.setText(datum);
                    namn5.setText(namn);
                    tid5.setText(tid);
                }

                lineCounter++;
            }

        } catch (IOException e) {
            System.out.println("Det gick inte att läsa in filen!");
            throw new RuntimeException(e);
        }

    }

    // Other methods related to the model's functionality

    public class HighScoreEntry {
        private String datum;
        private String namn;
        private String tid;

        public HighScoreEntry(String datum, String namn, String tid) {
            this.datum = datum;
            this.namn = namn;
            this.tid = tid;
        }

        public String getDatum() {
            return datum;
        }

        public String getNamn() {
            return namn;
        }

        public String getTid() {
            return tid;
        }
    }
}