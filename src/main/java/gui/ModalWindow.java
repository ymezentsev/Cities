package gui;

import javax.swing.*;
import java.awt.*;

//має викликатися на стан гри "завершено"

public class ModalWindow {
    String btnExit = "Exit";                //ті всі значення залежать від локалі, поки так
    String btnNewGame = "New game";
    String lblResultWin = "You win! Congrats!";
    String lblResultLoose = "You loose.. Maybe next time..";
    String titleResult = "Result";
    boolean win = false;            //флаг для виводу тексту у вікні для "Win" або "Loose"


    public void showModalDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, titleResult, true);
        dialog.setSize(200, 150);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        String lblResult;
        if(win){
            lblResult = lblResultWin;
        } else {
            lblResult = lblResultLoose;}

        JLabel label = new JLabel(lblResult);
        label.setHorizontalAlignment(JLabel.CENTER);

        JButton okButton = new JButton(btnNewGame);
        okButton.addActionListener(e -> {
            //todo newGame()                //має запускати нову гру
            dialog.dispose();
        });

        JButton cancelButton = new JButton(btnExit);
        cancelButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.getContentPane().add(contentPanel);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
