package Exceptions;

import java.util.Set;
import java.util.HashSet;

public class CityNameValidator {
    private Set<String> cityDatabase;

    public CityNameValidator(String[] cityDatabase) {
        this.cityDatabase = new HashSet<>();
        for (String cityName : cityDatabase) {
            this.cityDatabase.add(cityName);
        }
    }

    public boolean isCityInDatabase(String city) {
        return cityDatabase.contains(city.toLowerCase());
    }

    public String checkLastLetterException(String cityName) throws CityNameException {
        if (cityName.endsWith("ь") || cityName.endsWith("и")) {
            if (cityName.length() >= 2) {
                cityName = cityName.substring(0, cityName.length() - 2);
            }
        }
        return cityName;
    }
}