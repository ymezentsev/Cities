package highscores;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScoresProcessor {
    private final File scoresFile = new File("src/main/resources/highscores.txt");
    public HighScoresProcessor(){
    }

    public ArrayList<ScoreEntry> processNewEntry(ScoreEntry newScoreEntry){
        ArrayList<ScoreEntry> scores = readScoresFile();
        updateScoresTable(newScoreEntry, scores);
        writeScoresFile(scores);
        return scores;
    }
    private ArrayList<ScoreEntry> readScoresFile(){
        ArrayList<ScoreEntry> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(scoresFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String playerName = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    scores.add(new ScoreEntry(playerName, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return scores;
    }

    private void updateScoresTable(ScoreEntry newScoreEntry, ArrayList<ScoreEntry> scores) {
        scores.add(new ScoreEntry(newScoreEntry.getPlayerName(), newScoreEntry.getScore()));
        Collections.sort(scores);
        if (scores.size() > 10){
            scores.remove(10);
        }
    }

    private void writeScoresFile(ArrayList<ScoreEntry> scores){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoresFile))) {
            for (ScoreEntry score : scores) {
                String scoreLine = score.getPlayerName() + " " + score.getScore();
                writer.write(scoreLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

