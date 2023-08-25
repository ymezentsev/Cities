package Exclusions;

import java.util.Scanner;

public class ExceptionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть назву міста: ");
        String cityName = scanner.nextLine().trim();

        try {
            if (!CityNameValidator.isUkrainian(cityName)) {
                throw new NonUkrainianCityNameException("Введена назва міста не українською мовою.");
            }

            if (!CityDataValidation.isCityInDatabase(cityName)) {
                throw new CityDataValidationException("Цього міста немає в базі гри.");
            }

            cityName = CityNameProcessor.processCityName(cityName);

            System.out.println("Оброблена назва міста: " + cityName);
        } catch (NonUkrainianCityNameException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (CityDataValidationException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (CityNameProcessException e) {
            System.out.println("Помилка обробки назви міста: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
