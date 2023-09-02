package difficultyLevels;

import gameLogic.GameLogic;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

//class which control timer for user's answer
public class DifficultyLevelTimer {
    private final DifficultyLevel difficultyLevel;
    private final GameLogic gameLogic;
    @Setter
    private boolean isAnswerRight;
    @Setter
    private boolean isGameOver;

    public DifficultyLevelTimer(DifficultyLevel difficultyLevel, GameLogic gameLogic) {
        this.difficultyLevel = difficultyLevel;
        this.gameLogic = gameLogic;
        isAnswerRight = false;
        isGameOver = false;
    }

    //timer for user's answer
    public void timer(JLabel label) {
        if (difficultyLevel == DifficultyLevel.EASY) {
            return;
        }

        for (int counter = difficultyLevel.getTimeForAnswer(); counter >= 0; counter--) {
            if (counter <= 5) {
                label.setForeground(Color.RED);
            } else {
                label.setForeground(Color.BLUE);
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

            //if user or computer win stop timer
            if (isGameOver){
                return;
            }
        }
        //if timer value = 0 user lost
        gameLogic.endGame(gameLogic.getCountUserStep(), false);
    }
}
