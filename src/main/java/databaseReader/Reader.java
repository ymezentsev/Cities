package databaseReader;

import java.util.Set;

public interface Reader {
    Set<String> readCitiesToList(String source);
}
