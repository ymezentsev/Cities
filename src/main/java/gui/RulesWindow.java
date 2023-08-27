package gui;

import javax.swing.*;
import java.awt.*;

//Contains rules for "Cities" game.
public class RulesWindow {
    String btnOk = "Ok";                //ті всі значення залежать від локалі, поки так
    String txtRules = "Cities Game Rules:\n" +
            "\n" +
            "1. The objective of the game is to name a city that starts " +
            "with the last letter of the previously named city.\n" +
            "\n" +
            "2. The cities named must be real cities that exist in the UK.\n" +
            "\n" +
            "3. Each city can only be used once in a game.\n" +
            "\n" +
            "4. Game ends when computer run out of the words, or player enters \"I give up.\" instead of city name\n";
    String titleRules = "How to play \"Cities\" game";
    public RulesWindow(JFrame parentFrame){
        showModalDialog(parentFrame);
    }
    private void showModalDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, titleRules, true);
        dialog.setSize(600, 250);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(txtRules);
        textArea.setEditable(false);

        JButton closeButton = new JButton(btnOk);
        closeButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.getContentPane().add(panel);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}

