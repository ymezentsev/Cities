package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

//Contains rules for "Cities" game.
public class RulesWindow {
    private final String btnOk;
    private final String txtRules;
    private final String titleRules;
    public RulesWindow(ResourceBundle resourceBundle){
        this.txtRules = resourceBundle.getString("txtRules");
        this.btnOk = resourceBundle.getString("btnOk");
        this.titleRules = resourceBundle.getString("titleRules");

    }

    public void showModalDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, titleRules, true);
        dialog.setSize(500, 300);
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

