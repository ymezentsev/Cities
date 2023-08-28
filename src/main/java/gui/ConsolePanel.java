package gui;

import javax.swing.*;
import java.util.Locale;
import java.util.Properties;

class ConsolePanel extends JPanel {
    private Properties bundle;

    ConsolePanel(Locale locale, Properties bundle) {
        this.bundle = bundle;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField inputField = new JTextField();
        JButton makeMoveButton = new JButton(bundle.getProperty("makeMove"));

        add(Box.createVerticalStrut(80));
        add(inputField);
        add(Box.createVerticalStrut(5));
        add(makeMoveButton);
        add(Box.createHorizontalStrut(40));
        add(makeMoveButton);
        add(Box.createVerticalStrut(100));
    }
}
