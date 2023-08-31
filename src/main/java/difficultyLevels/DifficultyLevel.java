package difficultyLevels;

public enum DifficultyLevel {
    EASY(0),
    MEDIUM(10),
    HARD(10);
    private int timeForAnswer;

    DifficultyLevel(int timeForAnswer) {
        this.timeForAnswer = timeForAnswer;
    }

    public int getTimeForAnswer() {
        return timeForAnswer;
    }

    public void setTimeForAnswer(int timeForAnswer) {
        this.timeForAnswer = timeForAnswer;
    }
}
