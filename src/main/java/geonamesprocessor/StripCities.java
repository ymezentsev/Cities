package geonamesprocessor;

import java.io.*;

//utility class
//Strips txt file to plain geonames downloaded and unziped from GeoNames Postal Code datasets
//look at list.txt file to get (country) arg (for example: "PL" for Poland)
public class StripCities {
    public void stripFile(String country) {
        String filePath = "src/main/resources/temp/" + country +".txt";
        String outputFilePath = "src/main/resources/"+country+"_cities.txt"; // Path to your output file

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\t");
                if (words.length >= 3) {
                    String thirdWord = words[2];
                    writer.write(thirdWord);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
