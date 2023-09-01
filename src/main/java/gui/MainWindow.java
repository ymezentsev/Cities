package gui;

import difficultyLevels.DifficultyLevelTimer;
import gameLogic.GameLogic;
import highscores.ScoreEntry;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

public class MainWindow extends JFrame {
    private ResourceBundle resourceBundle;
    private JLabel timerLabel;
    private JTextField inputField;
    private JLabel computerLabel;
    private JFrame frame;
    private String makeMove;
    private String inputLabelText;
    private String computerLabelText;
    private DifficultyLevelTimer difficultyLevelTimer;
    private GameLogic gameLogic;
    // private String inputCity;

    public MainWindow(ResourceBundle resourceBundle, DifficultyLevelTimer difficultyLevelTimer, GameLogic gameLogic) {
        this.resourceBundle = resourceBundle;
        this.difficultyLevelTimer = difficultyLevelTimer;
        this.gameLogic = gameLogic;
        this.makeMove = resourceBundle.getString("makeMove");
        this.inputLabelText = resourceBundle.getString("inputLabel");
        this.computerLabelText = resourceBundle.getString("computerLabel");
        showWindow();
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public JLabel getComputerLabel() {
        return computerLabel;
    }

    public JFrame getFrame() {
        return frame;
    }

    private void showWindow() {
        frame = new JFrame(resourceBundle.getString("title"));
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
        inputField.addActionListener(e -> {
            makeMove();
        });

        JLabel inputLabel = new JLabel(inputLabelText);
        inputLabel.setHorizontalAlignment(JLabel.LEFT);

        JButton button = new JButton(makeMove);
        button.addActionListener(e -> {
            makeMove();
        });

        computerLabel = new JLabel(computerLabelText);
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

        JMenu helpMenu = new JMenu(resourceBundle.getString("btnHelp"));
        menuBar.add(helpMenu);

        JMenuItem highScoresItem = new JMenuItem(resourceBundle.getString("titleHighScores"));
        helpMenu.add(highScoresItem);
        highScoresItem.addActionListener(e -> {
                    //треба подумати як вивест вікно без передачі туди ScoreEntry
                    new HighScoresWindow(resourceBundle, new ScoreEntry("test", 0)).showWindow();
                }
        );

        JMenuItem rulesItem = new JMenuItem(resourceBundle.getString("btnRules"));
        helpMenu.add(rulesItem);
        rulesItem.addActionListener(e -> {
                    new RulesWindow(resourceBundle).showModalDialog(frame);
                }
        );

        JMenuItem aboutItem = new JMenuItem(resourceBundle.getString("btnAbout"));
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(e -> {
                    new AboutWindow(resourceBundle).showAboutDialog(frame);
                }
        );

        return menuBar;
    }

    private void makeMove() {
        String input = inputField.getText().toLowerCase().trim();
        if(input.isBlank()){
            return;
        }
        if (gameLogic.checks(input, getComputerLabel())) {
            inputField.setText("");
            difficultyLevelTimer.setAnswerRight(true);
        }
    }
}
