package geonamesprocessor;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//utility class
//download and unzip txt file with from GeoNames Postal Code datasets to resources/temp/
//look at list.txt file to get (country) arg (for example: "PL" for Poland)

public class DownloadAndUnzip {
    private static final String GEONAMES =  "https://download.geonames.org/export/zip/";
    private static final String destDirectory = "src/main/resources/temp";
    public static void downloadAndUnzip(String country){
        String countryZip = country+".zip";
        String downloadUrl = GEONAMES + countryZip;
        try {
            URL url = new URL(downloadUrl);
            File zipFile = new File(destDirectory, countryZip);
            FileUtils.copyURLToFile(url, zipFile);

            File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdir();
            }

            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zipInputStream = new ZipInputStream(fis);

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                File entryFile = new File(destDirectory, entryName);

                if (zipEntry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    FileOutputStream fos = new FileOutputStream(entryFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.close();
                }

                zipInputStream.closeEntry();
            }

            zipInputStream.close();
            fis.close();

            if(zipFile.exists()){
                if(zipFile.delete()){
                    System.out.println("Source file deleted.");
                }else{
                    System.out.println("Can delete file.");
                }
            }else{
                System.out.println("File not found.");
            }
            System.out.println("Download and unzip completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        downloadAndUnzip("JP");
        new StripCities().stripFile("JP");
    }
}
