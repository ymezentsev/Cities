package gui;

import difficultyLevels.DifficultyLevel;
import gameLogic.GameCore1;
import languages.LanguageSelector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ResourceBundle;

public class WelcomeWindow {
    private ResourceBundle resourceBundle;
    private String userName;
    private final String title;
    private final String welcomeText1;
    private final String welcomeText2;
    private final String welcomeText3;
    private final String easyRadioButtonName;
    private final String mediumRadioButtonName;
    private final String hardRadioButtonName;
    private final String radioButtonsTitle;
    private boolean repeatGame;
    private DifficultyLevel difficultyLevel;

    //called when the program started at first
    public WelcomeWindow() {
        this.title = "Cities of the world";
        this.welcomeText1 = "Welcome to the game \"Cities of the World\"";
        this.welcomeText2 = "To continue, enter your name";
        this.welcomeText3 = "and select the country of which cities you want to play";
        this.repeatGame = false;
        this.easyRadioButtonName = "Easy";
        this.mediumRadioButtonName = "Medium";
        this.hardRadioButtonName = "Hard";
        this.radioButtonsTitle = "Level";
        this.difficultyLevel = DifficultyLevel.EASY;
    }

    //called when the user wants to play again
    public WelcomeWindow(ResourceBundle resourceBundle, String userName) {
        this.repeatGame = true;
        this.userName = userName;
        this.resourceBundle = resourceBundle;
        this.title = resourceBundle.getString("title");
        this.welcomeText1 = resourceBundle.getString("repeatGameText") + ", " + userName + "?";
        this.welcomeText2 = resourceBundle.getString("welcomeText2");
        this.welcomeText3 = "";
        this.easyRadioButtonName = resourceBundle.getString("easyRadioButtonName");
        this.mediumRadioButtonName = resourceBundle.getString("mediumRadioButtonName");
        this.hardRadioButtonName = resourceBundle.getString("hardRadioButtonName");
        this.radioButtonsTitle = resourceBundle.getString("radioButtonsTitle");
        this.difficultyLevel = DifficultyLevel.EASY;
    }

    //draw welcome window
    public void showWindow() {
        JFrame frame = new JFrame(title);
        frame.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(490, 280));

        createGUI(frame);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //window elements are drawn relative to each other
    private void createGUI(JFrame frame) {
        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);

        JLabel text1 = new JLabel(welcomeText1);
        text1.setHorizontalAlignment(JLabel.CENTER);
        Font font = new Font("Arial", Font.BOLD, 16);
        text1.setFont(font);

        JLabel text2 = new JLabel(welcomeText2);
        text2.setHorizontalAlignment(JLabel.CENTER);

        JLabel text3 = new JLabel(welcomeText3);
        text3.setHorizontalAlignment(JLabel.CENTER);

        JRadioButton easyRadioButton = new JRadioButton(easyRadioButtonName, true);
        JRadioButton mediumRadioButton = new JRadioButton(mediumRadioButtonName);
        JRadioButton hardRadioButton = new JRadioButton(hardRadioButtonName);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(easyRadioButton);
        buttonGroup.add(mediumRadioButton);
        buttonGroup.add(hardRadioButton);

        easyRadioButton.addItemListener(e -> {
            difficultyLevel = DifficultyLevel.EASY;
        });
        mediumRadioButton.addItemListener(e -> {
            difficultyLevel = DifficultyLevel.MEDIUM;
        });
        hardRadioButton.addItemListener(e -> {
            difficultyLevel = DifficultyLevel.HARD;
        });

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.add(easyRadioButton);
        radioButtonPanel.add(mediumRadioButton);
        radioButtonPanel.add(hardRadioButton);
        Border border = BorderFactory.createEtchedBorder();
        Border borderTitle = BorderFactory.createTitledBorder(border, radioButtonsTitle, TitledBorder.CENTER, TitledBorder.CENTER);
        radioButtonPanel.setBorder(borderTitle);

        JButton uaButton = getButton("Міста України",
                "src/main/resources/images/ua_flag.png", "uk", "UA", frame);
        JButton gbButton = getButton("Great Britain cities",
                "src/main/resources/images/gb_flag.png", "en", "UK", frame);
        JButton svkButton = getButton("Mestá Slovenska",
                "src/main/resources/images/svk_flag.png", "sk", "SK", frame);
        JButton deButton = getButton("Städte Deutschlands",
                "src/main/resources/images/de_flag.png", "de", "DE", frame);
        JButton frButton = getButton("Villes de France",
                "src/main/resources/images/fr_flag.png", "fr", "FR", frame);
        JButton esButton = getButton("Ciudades de España",
                "src/main/resources/images/es_flag.png", "es", "ES", frame);

        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.addActionListener(e -> {
            userName = textField.getText().trim();
            if (!userName.isBlank()) {
                uaButton.setEnabled(true);
                gbButton.setEnabled(true);
                svkButton.setEnabled(true);
                deButton.setEnabled(true);
                frButton.setEnabled(true);
                esButton.setEnabled(true);
            }
        });

        //positioning window's elements relative each other
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(text1, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(text2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(text3, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(new JLabel(" "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(textField, gbc);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipady = -2;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        panel.add(radioButtonPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(uaButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(gbButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(svkButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(deButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(frButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(esButton, gbc);

        if (repeatGame) {
            uaButton.setEnabled(true);
            gbButton.setEnabled(true);
            svkButton.setEnabled(true);
            deButton.setEnabled(true);
            frButton.setEnabled(true);
            esButton.setEnabled(true);
            textField.setVisible(false);
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    //create button with language select
    private JButton getButton(String textButton, String imgPath, String language, String country, JFrame frame) {
        ImageIcon icon = resize(new ImageIcon(imgPath), 60, 25);

        JButton button = new JButton(textButton, icon);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setPreferredSize(new Dimension(155, 47));
        button.setEnabled(false);

        button.addActionListener(e -> {
            if (userName.isBlank()) {
                JOptionPane.showMessageDialog(frame, "You must enter a username!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                LanguageSelector languageSelector = new LanguageSelector();
                GameCore1 gameCore = new GameCore1(languageSelector.getResourceBundle(language, country), userName, difficultyLevel);
                gameCore.startGame();
            }
        });
        return button;
    }

    //icon for button resize to specified size
    private ImageIcon resize(ImageIcon image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(
                new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);
    }
}
