package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Properties;

class RightPanel extends JPanel {
    private Properties bundle;

    RightPanel(Locale locale, Properties bundle) {
        this.bundle = bundle;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton questionButton = new JButton("?");
        questionButton.setFont(new Font("Arial", Font.BOLD, 20));
        questionButton.setPreferredSize(new Dimension(20, 20));

        Color ral3000Color = new Color(196, 18, 48);
        questionButton.setForeground(ral3000Color); // Встановлюємо білий колір тексту
        questionButton.addActionListener(e -> {
            // поки виклик вікна з текстом "Правила гри", потім буде посилання на файл
            JOptionPane.showMessageDialog(this, "Правила гри", "Правила гри", JOptionPane.INFORMATION_MESSAGE);
        });

        Box questionBox = Box.createHorizontalBox();
        questionBox.add(questionButton);

        add(questionBox);

        JLabel computerResponseLabel = new JLabel(bundle.getProperty("computerLabel"));
        JLabel inputLabel = new JLabel(bundle.getProperty("inputLabel"));

        Font font = new Font("Arial", Font.PLAIN, 16);
        computerResponseLabel.setFont(font);
        inputLabel.setFont(font);

        Box inputBox = Box.createVerticalBox();
        inputBox.add(Box.createVerticalStrut(65));
        inputLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        inputBox.add(inputLabel);

        Box computerBox = Box.createVerticalBox();
        computerBox.add(Box.createVerticalStrut(28));
        computerResponseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        computerBox.add(computerResponseLabel);

        add(inputBox);
        add(computerBox);
    }
}
