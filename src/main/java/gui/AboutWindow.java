package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

public class AboutWindow{
    private final String titleAboutWindow;
    private final String btnOk;
    private final String txtAbout;
    private final String versionLabel;


    public AboutWindow(ResourceBundle resourceBundle) {
        this.titleAboutWindow = resourceBundle.getString("titleAboutWindow");
        this.btnOk = resourceBundle.getString("btnOk");
        this.txtAbout = resourceBundle.getString("txtAbout");
        this.versionLabel = resourceBundle.getString("versionLabel");
    }

    public void showAboutDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, titleAboutWindow, true);
        dialog.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));
        dialog.setTitle(titleAboutWindow);
        dialog.setSize(300, 250);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextArea textArea = new JTextArea(txtAbout);
        textArea.append(versionLabel);
        textArea.setEditable(false);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setAlignmentY(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 150));

        panel.add(scrollPane);

        JButton okButton = new JButton(btnOk);
        okButton.addActionListener(e -> dialog.dispose());
        panel.add(okButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}

