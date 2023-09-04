package gui;

import gui.design.GradientPanel;
import languages.LanguageSettingsDto;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//Contains rules for "Cities of the world" game
@AllArgsConstructor
public class RulesWindow {
    private final LanguageSettingsDto languageSettingsDto;

    public void showModalDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, languageSettingsDto.getTitleRules(), true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setSize(580, 290);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        GradientPanel innerPanel = new GradientPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBackgroundColor();

        JTextArea textArea = new JTextArea(languageSettingsDto.getTxtRules());
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JButton closeButton = new JButton(languageSettingsDto.getBtnOk());
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(closeButton);

        innerPanel.add(textArea, BorderLayout.CENTER);
        innerPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.getContentPane().add(innerPanel);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}