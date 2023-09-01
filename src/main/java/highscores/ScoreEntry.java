package highscores;

//class for single score entry, used to form "high scores" table.
//implement Comparable for ranking (sorting results)

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScoreEntry implements Comparable<ScoreEntry> {
    private final String playerName;
    private final int score;

    @Override
    public int compareTo(ScoreEntry o) {
        return Integer.compare(o.score, this.score);
    }
}
