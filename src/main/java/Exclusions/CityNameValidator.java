package Exclusions;

public class CityNameValidator {
    public static boolean isUkrainian(String text) {
        return text.matches("[\\u0400-\\u04FF]+");
    }
}