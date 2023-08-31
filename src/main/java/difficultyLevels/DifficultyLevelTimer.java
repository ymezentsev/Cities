package difficultyLevels;

import gameLogic.GameLogic;

import javax.swing.*;
import java.awt.*;

public class DifficultyLevelTimer {
    private DifficultyLevel difficultyLevel;
    private GameLogic gameLogic;
    private boolean isAnswerRight;

    public DifficultyLevelTimer(DifficultyLevel difficultyLevel, GameLogic gameLogic) {
        this.difficultyLevel = difficultyLevel;
        this.gameLogic = gameLogic;
        isAnswerRight = false;
    }

    public void setAnswerRight(boolean isAnswerRight) {
        this.isAnswerRight = isAnswerRight;
    }

    public void timer(JLabel label) {
        if (difficultyLevel == DifficultyLevel.EASY) {
            return;
        }

        for (int counter = difficultyLevel.getTimeForAnswer(); counter >= 0; counter--) {
            if (counter < 5) {
                label.setForeground(Color.RED);
            }
            label.setText(Integer.toString(counter));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //if user's answer correct start timer again
            if (isAnswerRight) {
                counter = difficultyLevel.getTimeForAnswer() + 1;
                isAnswerRight = false;
            }
        }
        //if timer value = 0 user lost
        gameLogic.endGame(gameLogic.getCountUserStep(), false);
    }
}
