package gameLogic;

import static gameLogic.GameCore.cities;

public class Validator {
    public boolean isCityCorrect(String inputCity){
        for (String city : cities) {
            if(city.equalsIgnoreCase(inputCity)){
                return true;
            }
        }
        return false;
    }

    public boolean isFirstLetterCorrect(String lastComputerCity, String inputCity) {
        char lastComputerCityChar = lastComputerCity.charAt(lastComputerCity.length() - 1);
        char firstInputChar = inputCity.charAt(0);
        return Character.toLowerCase(lastComputerCityChar) == Character.toLowerCase(firstInputChar);
    }
}
