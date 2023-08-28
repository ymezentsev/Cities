package gui;

//class for single score entry, used to form "high scores" table.
//implement Comparable for ranking (sorting results)

//can be converted to record

//record ScoreEntry(String playerName, int score) implements Comparable<ScoreEntry> {
//
//    @Override
//    public int compareTo(ScoreEntry o) {
//        return Integer.compare(o.score, this.score);
//    }
//}

class ScoreEntry implements Comparable<ScoreEntry> {
    private final String playerName;
    private final int score;

    public ScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoreEntry o) {
        return Integer.compare(o.score, this.score);
    }
}
