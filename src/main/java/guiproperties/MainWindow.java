package guiproperties;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.Locale;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Міста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        Locale locale = new Locale("uk", "UA");

        JPanel consolePanel = new ConsolePanel(locale);
        JPanel rightPanel = new RightPanel(locale);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
