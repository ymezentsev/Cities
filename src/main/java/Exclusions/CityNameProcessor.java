package Exclusions;

public class CityNameProcessor {
    public static String processCityName(String cityName) throws CityNameProcessException {
        if (cityName.endsWith("ь") || cityName.endsWith("ґ") || cityName.endsWith("й") || cityName.endsWith("ц")) {
            if (cityName.length() >= 2) {
                cityName = cityName.substring(0, cityName.length() - 1) + cityName.charAt(cityName.length() - 2);
            }
        }
        return cityName;
    }
}