import gui.WelcomeWindow;
import gui.design.GradientPanel;

public class AppLauncher {
    private static GradientPanel globalGradientPanel;
    public static void main(String[] args) {
        globalGradientPanel = new GradientPanel();
        WelcomeWindow welcomeWindow = new WelcomeWindow();
        welcomeWindow.showWindow();
    }
}
