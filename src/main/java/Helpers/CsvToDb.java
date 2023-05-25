package Helpers;

import java.io.FileNotFoundException;
import java.util.List;

public interface CsvToDb {
	void csvToDb(List<Course> courses, String csvFile) throws FileNotFoundException;
}
