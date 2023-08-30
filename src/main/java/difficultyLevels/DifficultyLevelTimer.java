package difficultyLevels;

import javax.swing.*;
import java.awt.*;

public class DifficultyLevelTimer {
    private DifficultyLevel difficultyLevel;
    private boolean isAnswerRight;
    private boolean timeOut;

    public DifficultyLevelTimer(DifficultyLevel difficultyLevel, boolean timeOut) {
        this.difficultyLevel = difficultyLevel;
        this.timeOut = timeOut;
        isAnswerRight = false;
    }

    public void setAnswerRight(boolean isAnswerRight) {
        this.isAnswerRight = isAnswerRight;
    }

    public void drawTimer(JLabel label) {
        if (difficultyLevel == DifficultyLevel.EASY) {
            return;
        }

        for (int counter = difficultyLevel.getTimeForAnswer(); counter >= 1; counter--) {
            if (counter > 5) {
                label.setForeground(Color.BLUE);
            } else {
                label.setForeground(Color.RED);
            }
            //перенести в main window
            Font font = new Font("Arial", Font.BOLD, 30);
            label.setFont(font);

            label.setText(Integer.toString(counter));
            System.out.println(counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // в случае правильного ответа запустить счетчик сначала
            if (isAnswerRight) {
                counter = difficultyLevel.getTimeForAnswer();
                isAnswerRight = false;
            }
        }
        timeOut = true;
    }
}
