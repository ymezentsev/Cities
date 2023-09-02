package languages;

import java.util.Locale;
import java.util.ResourceBundle;

//class that receives the user's language selection
//and returns an object of ResourceBundle class, which containing the language settings
public class LanguageSelector {
    public ResourceBundle getResourceBundle(String language, String country) {
        Locale locale = Locale.of(language, country);
        return ResourceBundle.getBundle("text", locale);
    }
}
