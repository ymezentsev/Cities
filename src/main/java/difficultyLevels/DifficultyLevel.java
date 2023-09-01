package difficultyLevels;

//enum with hard levels and time for answer for each level
public enum DifficultyLevel {
    EASY(0),
    MEDIUM(40),
    HARD(20);
    private final int timeForAnswer;

    DifficultyLevel(int timeForAnswer) {
        this.timeForAnswer = timeForAnswer;
    }

    public int getTimeForAnswer() {
        return timeForAnswer;
    }
}
