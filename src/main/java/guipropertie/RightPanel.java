package guipropertie;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class RightPanel {
    import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

    class RightPanel extends JPanel {
        private ResourceBundle bundle;

        RightPanel(Locale locale) {
            bundle = ResourceBundle.getBundle("guipropertie.messages", locale);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel computerResponseLabel = new JLabel(bundle.getString("computerLabel"));
            JLabel inputLabel = new JLabel(bundle.getString("inputLabel"));

            Font font = new Font("Arial", Font.PLAIN, 20);
            computerResponseLabel.setFont(font);
            inputLabel.setFont(font);

            Box inputBox = Box.createVerticalBox();
            inputBox.add(Box.createVerticalStrut(95));
            inputLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            inputBox.add(inputLabel);

            Box computerBox = Box.createVerticalBox();
            computerBox.add(Box.createVerticalStrut(20));
            computerResponseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            computerBox.add(computerResponseLabel);

            add(inputBox);
            add(computerBox);
        }
    }
}
