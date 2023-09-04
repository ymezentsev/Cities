package gui;

import gui.design.GradientPanel;
import languages.LanguageSettingsDto;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//This window shows information about game
@AllArgsConstructor
public class AboutWindow {
    private final LanguageSettingsDto languageSettingsDto;

    public void showAboutDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, languageSettingsDto.getTitleAboutWindow(), true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setTitle(languageSettingsDto.getTitleAboutWindow());
        dialog.setSize(300, 250);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(parentFrame);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextArea textArea = new JTextArea(languageSettingsDto.getTxtAbout());
        textArea.append(languageSettingsDto.getVersionLabel());
        textArea.setEditable(false);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setAlignmentY(Component.CENTER_ALIGNMENT);
        textArea.setBackground(new Color(228, 241, 255));
        textArea.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 150));

        panel.add(scrollPane);

        JButton okButton = new JButton(languageSettingsDto.getBtnOk());
        okButton.addActionListener(e -> dialog.dispose());
        panel.add(okButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
