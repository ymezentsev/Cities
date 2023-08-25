package guipropertie;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

class ConsolePanel extends JPanel {
    private ResourceBundle bundle;

    ConsolePanel(Locale locale) {
        bundle = ResourceBundle.getBundle("guipropertie.messages", locale);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField inputField = new JTextField();
        JButton makeMoveButton = new JButton(bundle.getString("makeMove"));

        add(Box.createVerticalStrut(80));
        add(inputField);
        add(makeMoveButton);
        add(Box.createVerticalStrut(100));
    }
}