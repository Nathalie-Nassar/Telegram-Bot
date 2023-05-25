package Main;

import Helpers.Course;

import java.io.File;
import java.util.List;

public class CourSeeraFactory implements Helpers.CourSeeraFactory{
    @Override
    public CourSeera createInstance(List<Course> courses) {
        CourSeera courSeera = new CourSeera();
        if (courses.isEmpty()){
            for (char c = 'a'; c <= 'z'; c++) {
                try {
                    CsvToDb object = new CsvToDb();
                    File csvFile = new File("C:\\Users\\student\\Desktop\\Bot\\src\\main\\java\\Main\\CSVFiles\\" + c + ".csv");
                    String csv = csvFile.getAbsolutePath();
                    object.csvToDb(courses, csv);
                } catch (Exception e) {
                }
            }
        }
        courSeera.courses = courses;
        return courSeera;
    }
}
