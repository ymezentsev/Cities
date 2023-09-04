package gui;

import difficultyLevels.DifficultyLevel;
import gameLogic.GameCore;
import gui.design.CustomButton;
import gui.design.GradientPanel;
import languages.LanguageSelector;
import languages.LanguageSettingsDto;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static gui.design.GradientPanel.*;

//Show welcome window when game start
public class WelcomeWindow {
    private final LanguageSettingsDto languageSettingsDto;
    private final String welcomeText1;
    private final String welcomeText2;
    private final String welcomeText3;
    private String userName;
    private final boolean repeatGame;
    private DifficultyLevel difficultyLevel;

    //called when the program started at first
    public WelcomeWindow() {
        languageSettingsDto = new LanguageSettingsDto(
                new LanguageSelector().getResourceBundle("en", "UK"));
        this.welcomeText1 = "Welcome to the game \"Cities of the World\"";
        this.welcomeText2 = "To continue, enter your name";
        this.welcomeText3 = "and select the country of which cities you want to play";
        this.repeatGame = false;
        this.difficultyLevel = DifficultyLevel.EASY;
    }

    //called when the user wants to play again
    public WelcomeWindow(LanguageSettingsDto languageSettingsDto, String userName) {
        this.repeatGame = true;
        this.userName = userName;
        this.languageSettingsDto = languageSettingsDto;
        this.welcomeText1 = languageSettingsDto.getRepeatGameText() + ", " + userName + "?";
        this.welcomeText2 = languageSettingsDto.getWelcomeText2();
        this.welcomeText3 = "";
        this.difficultyLevel = DifficultyLevel.EASY;
    }

    //draw welcome window
    public void showWindow() {
        JFrame frame = new JFrame(languageSettingsDto.getTitle());
        GradientPanel gradientPanel = new GradientPanel();
        frame.setContentPane(gradientPanel);
        frame.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new File("src/main/resources/images/mainIcon.jpg").toString()));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (repeatGame) {
            frame.setPreferredSize(new Dimension(490, 290));
        } else {
            frame.setPreferredSize(new Dimension(490, 325));
        }

        createGUI(frame);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //draws window elements relative to each other
    private void createGUI(JFrame frame) {
        GradientPanel panel = new GradientPanel();
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

        JRadioButton easyRadioButton = new JRadioButton(languageSettingsDto.getEasyRadioButtonName(), true);
        JRadioButton mediumRadioButton = new JRadioButton(languageSettingsDto.getMediumRadioButtonName());
        JRadioButton hardRadioButton = new JRadioButton(languageSettingsDto.getHardRadioButtonName());
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(easyRadioButton);
        buttonGroup.add(mediumRadioButton);
        buttonGroup.add(hardRadioButton);

        easyRadioButton.addItemListener(e -> difficultyLevel = DifficultyLevel.EASY);
        mediumRadioButton.addItemListener(e -> difficultyLevel = DifficultyLevel.MEDIUM);
        hardRadioButton.addItemListener(e -> difficultyLevel = DifficultyLevel.HARD);

        easyRadioButton.setForeground(new Color(0, 128, 0));
        mediumRadioButton.setForeground(new Color(0, 111, 255));
        hardRadioButton.setForeground(new Color(255, 0, 0));
        easyRadioButton.setOpaque(false);
        mediumRadioButton.setOpaque(false);
        hardRadioButton.setOpaque(false);

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setOpaque(false);
        radioButtonPanel.add(easyRadioButton);
        radioButtonPanel.add(mediumRadioButton);
        radioButtonPanel.add(hardRadioButton);
        Border border = BorderFactory.createEtchedBorder();
        Border borderTitle = BorderFactory.createTitledBorder(border, languageSettingsDto.getRadioButtonsTitle(),
                TitledBorder.CENTER, TitledBorder.CENTER);
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
        JButton worldButton = getButton("Cities of the world",
                "src/main/resources/images/world.png", "en", "US", frame);
        worldButton.setSize(180, 25);

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
                worldButton.setEnabled(true);
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

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        panel.add(worldButton, gbc);

        if (repeatGame) {
            uaButton.setEnabled(true);
            gbButton.setEnabled(true);
            svkButton.setEnabled(true);
            deButton.setEnabled(true);
            frButton.setEnabled(true);
            esButton.setEnabled(true);
            worldButton.setEnabled(true);
            textField.setVisible(false);
        }
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    //create button with select of country
    private JButton getButton(String textButton, String imgPath, String language, String country, JFrame frame) {
        ImageIcon icon = resize(new ImageIcon(imgPath), 60, 25);

        JButton button = new JButton(textButton, icon);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setPreferredSize(new Dimension(155, 47));
        button.setEnabled(false);
        button.setUI(new CustomButton());

        button.addActionListener(e -> {
            if (userName.isBlank()) {
                UIManager.put("OptionPane.background", new Color(MAIN_COLOR_VALUE_OF_RED, MAIN_COLOR_VALUE_OF_GREEN, MAIN_COLOR_VALUE_OF_BLUE));
                UIManager.put("Panel.background", new Color(MAIN_COLOR_VALUE_OF_RED, MAIN_COLOR_VALUE_OF_GREEN, MAIN_COLOR_VALUE_OF_BLUE));
                JOptionPane.showMessageDialog(frame, "You must enter a username!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                LanguageSelector languageSelector = new LanguageSelector();
                LanguageSettingsDto languageSettingsDto = new LanguageSettingsDto(languageSelector.getResourceBundle(language, country));
                GameCore gameCore = new GameCore(languageSettingsDto, userName, difficultyLevel);
                gameCore.startGame();
            }
        });
        return button;
    }

    //icon for button resize to specified size
    private ImageIcon resize(ImageIcon image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = bi.createGraphics();
        g2d.addRenderingHints(
                new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);
    }
}
