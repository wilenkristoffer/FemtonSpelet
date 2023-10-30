package uppgift3.femtonspelet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoreModel {
    private final List<HighScoreEntry> highScores = new ArrayList<>();

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
    //Metod som läser in filen och delar upp datum, namn och tid.
    public void loadHighScoresFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String datum = parts[0];
                String namn = parts[1];
                String tid = parts[2];

                addHighScore(datum, namn, tid);
            }
            sortHighScores();
        } catch (IOException e) {
            System.out.println("Fel vid inläsning av fil.");
            throw new RuntimeException(e);
        }
    }
    //Klass som har hand om data för listan, vi skapar upp och hanterar instanser av klassen.
    //När vi läser från filen så skapar vi upp ett objekt för varje "inlägg" vi har i filen och adderar de till listan.
    //Kan göras till en Record klass.
    public static class HighScoreEntry {
        private final String datum;
        private final String namn;
        private final String tid;

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