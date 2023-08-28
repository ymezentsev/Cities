package Exceptions;

public class CityNameValidator {
    private String[] cityDatabase;

    public CityNameValidator(String[] cityDatabase) {
        this.cityDatabase = cityDatabase;
    }

    public boolean isCityInDatabase(String city) {
        for (String cityName : cityDatabase) {
            if (city.equalsIgnoreCase(cityName)) {
                return true;
            }
        }
        return false;
    }

    public String processCityName(String cityName) throws CityNameProcessException {
        if (cityName.endsWith("ь") || cityName.endsWith("и")) {
            if (cityName.length() >= 2) {
                cityName = cityName.substring(0, cityName.length() - 1);
            }
        }
        return cityName;
    }
}
