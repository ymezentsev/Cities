package gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Міста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        Locale locale = new Locale("uk", "UA");
        Properties bundle = loadPropertiesFromFile("UA_cities.txt");

        JPanel consolePanel = new ConsolePanel(locale, bundle);
        RightPanel rightPanel = new RightPanel(locale, bundle); // Зміна імпорту

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(consolePanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.5);

        splitPane.setDividerSize(5);

        BasicSplitPaneUI ui = (BasicSplitPaneUI) splitPane.getUI();
        BasicSplitPaneDivider divider = ui.getDivider();
        divider.setDividerSize(5);
        divider.setLayout(new BorderLayout());

        getContentPane().setBackground(Color.DARK_GRAY);
        add(splitPane);
    }

    private Properties loadPropertiesFromFile(String filename) {
        Properties properties = new Properties();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    properties.setProperty(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
