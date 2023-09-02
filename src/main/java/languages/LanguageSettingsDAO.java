package languages;

import lombok.Getter;

import java.util.ResourceBundle;

//class which contains language-depending data
@Getter
public class LanguageSettingsDAO {
    //WelcomeWindow
    private final String title;
    private final String welcomeText2;
    private final String repeatGameText;
    private final String easyRadioButtonName;
    private final String mediumRadioButtonName;
    private final String hardRadioButtonName;
    private final String radioButtonsTitle;

    //GameCore
    private final String fileName;
    private final String exitWord;
    private final String errorTitle;
    private final String errorFirstLetter;
    private final String errorCity;
    private final String repeatCity;
    private final String lastLetterMessageTitle;
    private final String lastLetterException;

    //HighScoresWindow
    private final String btnExit;
    private final String columnPlayer;
    private final String columnRank;
    private final String columnScore;
    private final String titleHighScores;

    //ExitWindow
    private final String btnNewGame;
    private final String lblResultWin;
    private final String lblResultLoose;
    private final String titleResult;
    private final String lblScore;

    //RulesWindow
    private final String btnOk;
    private final String txtRules;
    private final String titleRules;

    //MainWindow
    private final String makeMove;
    private final String computerLabel;
    private final String inputLabel;
    private final String btnHelp;
    private final String btnRules;
    private final String btnAbout;

    //AboutWindow
    private final String titleAboutWindow;
    private final String txtAbout;
    private final String versionLabel;

    public LanguageSettingsDAO(ResourceBundle resourceBundle) {
        this.title = resourceBundle.getString("title");
        this.welcomeText2 = resourceBundle.getString("welcomeText2");
        this.repeatGameText = resourceBundle.getString("repeatGameText");
        this.easyRadioButtonName = resourceBundle.getString("easyRadioButtonName");
        this.mediumRadioButtonName = resourceBundle.getString("mediumRadioButtonName");
        this.hardRadioButtonName = resourceBundle.getString("hardRadioButtonName");
        this.radioButtonsTitle = resourceBundle.getString("radioButtonsTitle");
        this.fileName = resourceBundle.getString("fileName");
        this.exitWord = resourceBundle.getString("exitWord");
        this.errorTitle = resourceBundle.getString("errorTitle");
        this.errorFirstLetter = resourceBundle.getString("errorFirstLetter");
        this.errorCity = resourceBundle.getString("errorCity");
        this.repeatCity = resourceBundle.getString("repeatCity");
        this.lastLetterMessageTitle = resourceBundle.getString("lastLetterMessageTitle");
        this.lastLetterException = resourceBundle.getString("lastLetterException");
        this.btnExit = resourceBundle.getString("btnExit");
        this.columnPlayer = resourceBundle.getString("columnPlayer");
        this.columnRank = resourceBundle.getString("columnRank");
        this.columnScore = resourceBundle.getString("columnScore");
        this.titleHighScores = resourceBundle.getString("titleHighScores");
        this.btnNewGame = resourceBundle.getString("btnNewGame");
        this.lblResultWin = resourceBundle.getString("lblResultWin");
        this.lblResultLoose = resourceBundle.getString("lblResultLoose");
        this.titleResult = resourceBundle.getString("titleResult");
        this.lblScore = resourceBundle.getString("lblScore");
        this.btnOk = resourceBundle.getString("btnOk");
        this.txtRules = resourceBundle.getString("txtRules");
        this.titleRules = resourceBundle.getString("titleRules");
        this.makeMove = resourceBundle.getString("makeMove");
        this.computerLabel = resourceBundle.getString("computerLabel");
        this.inputLabel = resourceBundle.getString("inputLabel");
        this.btnHelp = resourceBundle.getString("btnHelp");
        this.btnRules = resourceBundle.getString("btnRules");
        this.btnAbout = resourceBundle.getString("btnAbout");
        this.titleAboutWindow = resourceBundle.getString("titleAboutWindow");
        this.txtAbout = resourceBundle.getString("txtAbout");
        this.versionLabel = resourceBundle.getString("versionLabel");
    }
}
