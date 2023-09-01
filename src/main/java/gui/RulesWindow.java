package gui;

import languages.LanguageSettingsDAO;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//Contains rules for "Cities of the world" game
@AllArgsConstructor
public class RulesWindow {
    private final LanguageSettingsDAO languageSettingsDAO;

    public void showModalDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, languageSettingsDAO.getTitleRules(), true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setSize(500, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(languageSettingsDAO.getTxtRules());
        textArea.setEditable(false);

        JButton closeButton = new JButton(languageSettingsDAO.getBtnOk());
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

