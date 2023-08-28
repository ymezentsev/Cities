import gui.WelcomeWindow;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppLauncher {
    public static void main(String[] args) {
      /*  Locale locale = new Locale("sk", "SK");
        ResourceBundle bundle = ResourceBundle.getBundle("text", locale);
        WelcomeWindow welcomeWindow = new WelcomeWindow(bundle, "user1");
        welcomeWindow.showWindow();*/

        WelcomeWindow welcomeWindow = new WelcomeWindow();
        welcomeWindow.showWindow();
    }
}
