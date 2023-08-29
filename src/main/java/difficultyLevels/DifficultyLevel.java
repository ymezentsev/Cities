package difficultyLevels;

public enum DifficultyLevel {
    EASY(22),
    MEDIUM(40),
    HARD(20);
    private int time;

    DifficultyLevel(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
