package languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSelector {
    public ResourceBundle getResourceBundle(String language, String country) {
        Locale locale = new Locale(language, country);
        return ResourceBundle.getBundle("text", locale);
    }
}
