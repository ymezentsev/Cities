package Exclusions;

public class CityDataValidation {
    //поки приклад короткої бази даних для перевірки роботи
    private static final String[] cityDatabase = {
            "Львів", "Київ", "Харків", "Одеса", "Дніпро",
            "Запоріжжя", "Луцьк", "Рівне", "Тернопіль", "Ужгород",
            "Івано-Франківськ", "Чернівці", "Хмельницький", "Вінниця",
            "Житомир", "Полтава", "Суми", "Черкаси", "Ямпіль"
    };

    public static boolean isCityInDatabase(String city) {
        for (String cityName : cityDatabase) {
            if (city.equalsIgnoreCase(cityName)) {
                return true;
            }
        }
        return false;
    }
}