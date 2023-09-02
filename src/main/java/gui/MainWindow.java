package gui;

import difficultyLevels.DifficultyLevelTimer;
import gameLogic.GameLogic;
import languages.LanguageSettingsDAO;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

//Show main window
public class MainWindow {
    private final LanguageSettingsDAO languageSettingsDAO;
    private final DifficultyLevelTimer difficultyLevelTimer;
    private final GameLogic gameLogic;
    @Getter
    private JLabel timerLabel;
    @Getter
    private JLabel computerLabel;
    @Getter
    private JFrame frame;
    private JTextField inputField;

    public MainWindow(LanguageSettingsDAO languageSettingsDAO, DifficultyLevelTimer difficultyLevelTimer, GameLogic gameLogic) {
        this.languageSettingsDAO = languageSettingsDAO;
        this.difficultyLevelTimer = difficultyLevelTimer;
        this.gameLogic = gameLogic;
        showWindow();
    }

    private void showWindow() {
        frame = new JFrame(languageSettingsDAO.getTitle());
        frame.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));

        createGUI(frame);

        frame.setJMenuBar(createMenuBar(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createGUI(JFrame frame) {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagLayout gridBagLayout = new GridBagLayout();
        contentPanel.setLayout(gridBagLayout);

        timerLabel = new JLabel("  ", JLabel.CENTER);
        timerLabel.setForeground(Color.BLUE);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timerLabel.setPreferredSize(new Dimension(60, 30));
        Border border = BorderFactory.createEtchedBorder();
        timerLabel.setBorder(border);

        inputField = new JTextField();
        inputField.setHorizontalAlignment(JLabel.CENTER);
        inputField.addActionListener(e -> makeMove());

        JLabel inputLabel = new JLabel(languageSettingsDAO.getInputLabel());
        inputLabel.setHorizontalAlignment(JLabel.LEFT);

        JButton button = new JButton(languageSettingsDAO.getMakeMove());
        button.addActionListener(e -> makeMove());

        computerLabel = new JLabel(languageSettingsDAO.getComputerLabel());
        computerLabel.setHorizontalAlignment(JLabel.LEFT);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        contentPanel.add(timerLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        contentPanel.add(new JLabel(" "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPanel.add(inputField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPanel.add(new JLabel("       "), gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPanel.add(inputLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        contentPanel.add(new JLabel(" "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        contentPanel.add(button, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        contentPanel.add(new JLabel("       "), gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        contentPanel.add(computerLabel, gbc);

        frame.setContentPane(contentPanel);
    }

    private JMenuBar createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu(languageSettingsDAO.getBtnHelp());
        menuBar.add(helpMenu);

        JMenuItem highScoresItem = new JMenuItem(languageSettingsDAO.getTitleHighScores());
        helpMenu.add(highScoresItem);
        highScoresItem.addActionListener(e -> new HighScoresWindow(languageSettingsDAO).showWindow());
        highScoresItem.setAccelerator(KeyStroke.getKeyStroke("alt S"));

        JMenuItem rulesItem = new JMenuItem(languageSettingsDAO.getBtnRules());
        helpMenu.add(rulesItem);
        rulesItem.addActionListener(e -> new RulesWindow(languageSettingsDAO).showModalDialog(frame));
        rulesItem.setAccelerator(KeyStroke.getKeyStroke("alt R"));

        JMenuItem aboutItem = new JMenuItem(languageSettingsDAO.getBtnAbout());
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(e -> new AboutWindow(languageSettingsDAO).showAboutDialog(frame));
        aboutItem.setAccelerator(KeyStroke.getKeyStroke("alt A"));

        return menuBar;
    }

    private void makeMove() {
        String input = inputField.getText().toLowerCase().trim();
        if (input.isBlank()) {
            return;
        }
        if (gameLogic.checks(input, getComputerLabel())) {
            inputField.setText("");
            difficultyLevelTimer.setAnswerRight(true);
        }
    }
}
