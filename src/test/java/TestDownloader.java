import Helpers.Course;
import Main.CsvToDb;
import Main.Downloader;
import Main.HtmlToCsv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDownloader {
    @Test
    @DisplayName("Downloader Test")
    void test1() throws IOException {
        Downloader downloader = new Downloader();
        Boolean state = true;
        for (char i = 'a'; i <= 'z'; i++) {
            String htmlPage = "https://www-banner.aub.edu.lb/catalog/schd_" + Character.toUpperCase(i) + ".htm";
            File txtFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\HtmlFiles\\" + i + ".html");
            String txt = txtFile.getAbsolutePath();
            downloader.downloadHtmlToFile(htmlPage, txt);
            state = state && txtFile.exists();
        }
        assertTrue(state);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 1")
        // To check that we that the create csv file from html file worked
    void test2() {
        boolean state = false;
        for (char i = 'a'; i <= 'z'; i++) {
            File txtFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\htmlFiles\\" + i + ".html");
            File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\" + i + ".csv");
            String txt = txtFile.getAbsolutePath();
            String csv = csvFile.getAbsolutePath();
            HtmlToCsv htmlToCsv = new HtmlToCsv();
            htmlToCsv.htmlToCsv(txt, csv);
            if (csvFile.exists()) {
                state = true;
            }
        }
        assertTrue(state);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 2")
        // To check that we didn't create csv 26 files, as there are some files that wont be created because of there are some empty alphabets characters with no courses.
    void test3() {
        int counter = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\" + i + ".csv");
            if (csvFile.exists()) {
                counter++;
            }
        }
        assertTrue(counter <= 26);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 3")
    void test4() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\a.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 229);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 4")
    void test5() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\b.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 395);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 5")
    void test6() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\d.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 14);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 6")
    void test7() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\e.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 710);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 7")
    void test8() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\f.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 60);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 8")
    void test9() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\g.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 52);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 9")
    void test10() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\h.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 70);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 10")
    void test11() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\i.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 41);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 11")
    void test12() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\m.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 444);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 12")
    void test13() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\n.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 151);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 13")
    void test14() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\o.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 9);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 14")
    void test15() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\p.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 478);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 15")
    void test16() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\r.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 11);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 16")
    void test17() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\s.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 100);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 17")
    void test18() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\t.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 15);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 18")
    void test19() throws IOException {
        File CsvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\u.csv");
        long count = Files.lines(Path.of(String.valueOf(CsvFile))).count();
        assertEquals(count, 30);
    }

    @Test
    @DisplayName("HTMLtoCSV Test 19")
    void test20() {
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\j.csv");
        assertFalse(csvFile.exists());
    }

    @Test
    @DisplayName("HTMLtoCSV Test 20")
    void test21() {
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\k.csv");
        assertFalse(csvFile.exists());
    }

    @Test
    @DisplayName("CSVtoDB Test 1")
    void test22() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\a.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 228);
    }

    @Test
    @DisplayName("CSVtoDB Test 2")
    void test23() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\b.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 394);
    }

    @Test
    @DisplayName("CSVtoDB Test 3")
    void test24() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\c.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 488);
    }
    @Test
    @DisplayName("CSVtoDB Test 4")
    void test25() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\d.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 13);
    }
    @Test
    @DisplayName("CSVtoDB Test 5")
    void test26() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\e.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 709);
    }
    @Test
    @DisplayName("CSVtoDB Test 6")
    void test27() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\f.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 59);
    }
    @Test
    @DisplayName("CSVtoDB Test 7")
    void test28() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\g.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 51);
    }
    @Test
    @DisplayName("CSVtoDB Test 8")
    void test29() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\h.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 69);
    }
    @Test
    @DisplayName("CSVtoDB Test 9")
    void test30() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\i.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 40);
    }
    @Test
    @DisplayName("CSVtoDB Test 10")
    void test31() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\l.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 28);
    }
    @Test
    @DisplayName("CSVtoDB Test 11")
    void test32() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\m.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 443);
    }
    @Test
    @DisplayName("CSVtoDB Test 12")
    void test33() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\n.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 150);
    }
    @Test
    @DisplayName("CSVtoDB Test 13")
    void test34() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\o.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 8);
    }
    @Test
    @DisplayName("CSVtoDB Test 14")
    void test35() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\p.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 477);
    }
    @Test
    @DisplayName("CSVtoDB Test 15")
    void test36() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\r.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 10);
    }
    @Test
    @DisplayName("CSVtoDB Test 16")
    void test37() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\s.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 99);
    }
    @Test
    @DisplayName("CSVtoDB Test 17")
    void test38() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\t.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 14);
    }
    @Test
    @DisplayName("CSVtoDB Test 18")
    void test39() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        CsvToDb object = new CsvToDb();
        File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\u.csv");
        String csv = csvFile.getAbsolutePath();
        object.csvToDb(allCourses, csv);
        assertEquals(allCourses.size() , 29);
    }
    @Test
    @DisplayName("CSVtoDB Test 19")
    void test40() throws FileNotFoundException {
        List<Course> allCourses = new LinkedList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            try {
                CsvToDb object = new CsvToDb();
                File csvFile = new File("C:\\Users\\student\\Desktop\\Fall 2021\\CMPS 252\\Assignments\\asst4\\src\\Main\\CSVFiles\\"+c+".csv");
                String csv = csvFile.getAbsolutePath();
                object.csvToDb(allCourses, csv);}
            catch (Exception e){}}
        assertEquals(allCourses.size() , 3309);
    }
}
