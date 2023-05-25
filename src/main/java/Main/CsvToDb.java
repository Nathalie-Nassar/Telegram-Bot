package Main;

import Helpers.Course;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class CsvToDb implements Helpers.CsvToDb {
    // requires: a CSV file that lists the courses
    // effects: a list of all the courses, that are parsed through the csv file
    @Override
    public void csvToDb(List<Course> courses, String csvFile) throws FileNotFoundException {
        Scanner console = new Scanner(new FileReader(csvFile));
        console.nextLine();
        while (console.hasNextLine()) {
            String[] str = console.nextLine().split(",");
            Main.Course courseObject = new Main.Course();
            courseObject.Crn = str[0];
            courseObject.Subject = str[1];
            courseObject.CourseNumber = str[2];
            courseObject.Section = str[3];
            courseObject.Title = str[4];
            courseObject.CreditsHours = Float.parseFloat(str[5]);
            courseObject.College = str[6];
            courseObject.ActualEnrollement = Integer.parseInt(str[7]);
            courseObject.SeatsAvailable = Integer.parseInt(str[8]);
            try {
                courseObject.beginTime = LocalTime.parse(str[9]);
                courseObject.endTime = LocalTime.parse(str[10]);
            } catch (Exception e) {
                courseObject.beginTime = null;
                courseObject.endTime = null;
            }
            courseObject.Building = str[11];
            courseObject.Room = str[12];
            courseObject.Monday = Boolean.parseBoolean(str[13]);
            courseObject.Tuesday = Boolean.parseBoolean(str[14]);
            courseObject.Wednesday = Boolean.parseBoolean(str[15]);
            courseObject.Thursday = Boolean.parseBoolean(str[16]);
            courseObject.Friday = Boolean.parseBoolean(str[17]);
            courseObject.Saturday = Boolean.parseBoolean(str[18]);
            courseObject.InstructorFirstName = str[19];
            courseObject.InstructorLastName = str[20];
            courses.add(courseObject);
        }
    }
}
