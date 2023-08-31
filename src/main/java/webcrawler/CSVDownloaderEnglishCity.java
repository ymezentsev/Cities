package webcrawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDownloaderEnglishCity {
    public static void columnValues() {
        String csvUrl = "https://drive.google.com/uc?export=download&id=1hwJVArDgybh4S41HYyP1Stj4gUDLGoop";

        try {
            List<String[]> csvData = downloadAndParseCSV(csvUrl);

            List<String> column2Values = new ArrayList<>();
            for (int i = 2; i < csvData.size(); i++) {
                String[] row = csvData.get(i);
                if (row.length >= 2) {
                    column2Values.add(row[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> downloadAndParseCSV(String url) throws IOException {
        List<String[]> csvData = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String content = EntityUtils.toString(entity);
                    String[] lines = content.split("\n");
                    for (String line : lines) {
                        csvData.add(line.split(";"));
                    }
                }
            }
        }

        return csvData;
    }
}