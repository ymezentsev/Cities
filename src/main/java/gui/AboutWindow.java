package gui;

import languages.LanguageSettingsDAO;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//This window shows information about game
@AllArgsConstructor
public class AboutWindow{
    private final LanguageSettingsDAO languageSettingsDAO;

    public void showAboutDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, languageSettingsDAO.getTitleAboutWindow(), true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setTitle(languageSettingsDAO.getTitleAboutWindow());
        dialog.setSize(300, 250);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextArea textArea = new JTextArea(languageSettingsDAO.getTxtAbout());
        textArea.append(languageSettingsDAO.getVersionLabel());
        textArea.setEditable(false);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setAlignmentY(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 150));

        panel.add(scrollPane);

        JButton okButton = new JButton(languageSettingsDAO.getBtnOk());
        okButton.addActionListener(e -> dialog.dispose());
        panel.add(okButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}

