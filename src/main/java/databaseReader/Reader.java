package databaseReader;

import java.util.List;

public interface Reader {
    List<String> readCitiesToList(String source);
}
