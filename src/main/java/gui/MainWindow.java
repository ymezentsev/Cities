package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

public class MainWindow {
    private ResourceBundle resourceBundle;
    private String userName;
    private String makeMove;
    private String inputLabelText;
    private String computerLabelText;
    private String countdown;

    public MainWindow(ResourceBundle resourceBundle, String userName) {
        this.resourceBundle = resourceBundle;
        this.userName = userName;
        this.makeMove = resourceBundle.getString("makeMove");
        this.inputLabelText = resourceBundle.getString("inputLabel");
        this.computerLabelText = resourceBundle.getString("computerLabel");
        this.countdown = resourceBundle.getString("countdown");
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame(resourceBundle.getString("title"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));

        JPanel contentPane = new JPanel(new GridBagLayout());
        frame.setContentPane(contentPane);

        GridBagConstraints countdownLabelGbc = new GridBagConstraints();
        countdownLabelGbc.gridx = 0;
        countdownLabelGbc.gridy = 0;
        countdownLabelGbc.gridwidth = 2;
        countdownLabelGbc.anchor = GridBagConstraints.CENTER;
        JLabel countdownLabel = new JLabel(countdown);
        countdownLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        countdownLabelGbc.insets = new Insets(50, 0, -40, 0);
        contentPane.add(countdownLabel, countdownLabelGbc);

        GridBagConstraints contentPaneGbc = new GridBagConstraints();
        contentPaneGbc.gridx = 0;
        contentPaneGbc.gridy = 1;
        contentPaneGbc.fill = GridBagConstraints.BOTH;
        contentPaneGbc.weightx = 1.0;
        contentPaneGbc.weighty = 1.0;

        JPanel leftPanel = createInputAndButtonPanel();
        contentPane.add(leftPanel, contentPaneGbc);

        GridBagConstraints rightPanelGbc = new GridBagConstraints();
        rightPanelGbc.gridx = 1;
        rightPanelGbc.gridy = 1;
        rightPanelGbc.fill = GridBagConstraints.BOTH;
        rightPanelGbc.weightx = 1.0;
        rightPanelGbc.weighty = 1.0;

        JPanel rightPanel = createLabelPanel();
        contentPane.add(rightPanel, rightPanelGbc);

        frame.setJMenuBar(createMenuBar());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createInputAndButtonPanel() {
        JPanel inputAndButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField inputField = new JTextField();
        inputField.setColumns(15);
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputField.setPreferredSize(new Dimension(89, 70));
        inputAndButtonPanel.add(inputField, gbc);

        JButton button = new JButton(makeMove);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        button.setPreferredSize(new Dimension(189, 40));
        inputAndButtonPanel.add(button, gbc);

        return inputAndButtonPanel;
    }

    private JPanel createLabelPanel() {
        JPanel labelPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel inputLabel = new JLabel(inputLabelText);
        inputLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        labelPanel.add(inputLabel, gbc);

        JLabel computerLabel = new JLabel(computerLabelText);
        computerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(40, 0, 0, 10);
        labelPanel.add(computerLabel, gbc);

        return labelPanel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu(resourceBundle.getString("btnHelp"));
        menuBar.add(helpMenu);

        JMenuItem highScoresItem = new JMenuItem(resourceBundle.getString("titleHighScores"));
        helpMenu.add(highScoresItem);

        JMenuItem rulesItem = new JMenuItem(resourceBundle.getString("btnRules"));
        helpMenu.add(rulesItem);

        JMenuItem aboutItem = new JMenuItem(resourceBundle.getString("btnAbout"));
        helpMenu.add(aboutItem);

        return menuBar;
    }
}
