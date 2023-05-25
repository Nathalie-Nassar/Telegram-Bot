import Helpers.Course;
import Helpers.DayOfWeek;
import Helpers.Room;
import Helpers.Schedule;
import Main.CourSeeraFactory;
import Main.CsvToDb;
import Main.Instructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourSeeraTests {
    List<Course> courseList = new LinkedList<>();
    void fillList(){
        if (courseList.isEmpty()){
            for (char c = 'a'; c <= 'z'; c++) {
                try {
                    CsvToDb object = new CsvToDb();
                    File csvFile = new File("C:\\Users\\student\\Desktop\\Bot\\src\\main\\java\\Main\\CSVFiles\\" + c + ".csv");
                    String csv = csvFile.getAbsolutePath();
                    object.csvToDb(courseList, csv);
                } catch (Exception e) {
                }
            }
        }
    }
    @Test
    @DisplayName("Room Schedule Test1")
    void test1() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
        assertEquals(map.size(), 211);
    }

    @Test
    @DisplayName("Room Schedule Test2 (Number of rooms in Nicely)")
    void test2() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
        int NicelyNumbers = 0;
        for(Room room : map.keySet()){
            if (room.getBuilding().trim().toUpperCase().equals("NICELY")) NicelyNumbers++;
        }
        assertEquals(NicelyNumbers, 38);
    }

    @Test
    @DisplayName("Room Schedule Test3 (Number of rooms in Bliss)")
    void test3() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
        int BlissNumber = 0;
        for(Room room : map.keySet()){
            if (room.getBuilding().trim().toUpperCase().equals("BLISS")) BlissNumber++;
        }
        assertEquals(BlissNumber, 9);
    }

    @Test
    @DisplayName("Room Schedule Test4 (Number of rooms in ADAD)")
    void test4() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
        int ADADNumber = 0;
        for(Room room : map.keySet()){
            if (room.getBuilding().trim().toUpperCase().equals("ADAD")) ADADNumber++;
        }
        assertEquals(ADADNumber, 2);
    }
    @Test
    @DisplayName("Room Schedule Test5 (Number of rooms in VANDYK)")
    void test5() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
        int VANDYKNumber = 0;
        for(Room room : map.keySet()){
            if (room.getBuilding().trim().toUpperCase().equals("VANDYK")) VANDYKNumber++;
        }
        assertEquals(VANDYKNumber, 7);
    }
    @Test
    @DisplayName("Room Schedule Test6 (Number of rooms in FISK)")
    void test6() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
        int FISKNumber = 0;
        for(Room room : map.keySet()){
            if (room.getBuilding().trim().toUpperCase().equals("FISK")) FISKNumber++;
        }
        assertEquals(FISKNumber, 9);
    }
    @Test
    @DisplayName("Room Schedule Test7 (Number of rooms in OSB)")
    void test7() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
        int OSBNumber = 0;
        for(Room room : map.keySet()){
            if (room.getBuilding().trim().toUpperCase().equals("OSB")) OSBNumber++;
        }
        assertEquals(OSBNumber, 15);
    }

    @Test
    @DisplayName("Room Schedule Test8 (Number of Class thought in Bliss 205)")
    void test8() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Main.Room room = new Main.Room();
        room.Building = "BLISS";
        room.RoomNumber = "205";

        List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room);
        assertEquals(list1.size(), 14);
    }
    @Test
    @DisplayName("Room Schedule Test9 (Class thought in Bliss 205 on Monday)")
    void test9() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Main.Room room = new Main.Room();
        room.Building = "BLISS";
        room.RoomNumber = "205";

        List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room , DayOfWeek.Monday);
        assertEquals(list1.size(), 8);
    }
    @Test
    @DisplayName("Room Schedule Test10 (Class thought in Bliss 205 on Tuesday)")
    void test10() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Main.Room room = new Main.Room();
        room.Building = "BLISS";
        room.RoomNumber = "205";

        List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room , DayOfWeek.Tuesday);
        assertEquals(list1.size(), 5);
    }
    @Test
    @DisplayName("Room Schedule Test11 (Class thought in Bliss 205 on 19 November 2021)")
    void test11() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Main.Room room = new Main.Room();
        room.Building = "BLISS";
        room.RoomNumber = "205";
        List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room , LocalDate.parse("2021-11-19"));
        assertEquals(list1.size(), 8);
    }
    @Test
    @DisplayName("Room Schedule Test12 (Class thought in Bliss 205 on 18 November 2021)")
    void test12() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Main.Room room = new Main.Room();
        room.Building = "BLISS";
        room.RoomNumber = "205";

        List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room , LocalDate.parse("2021-11-18"));
        assertEquals(list1.size(), 5);
    }
    @Test
    @DisplayName("Room Schedule Test13 (Class thought in Bliss 205 on 18 November 2021)")
    void test13() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Main.Room room = new Main.Room();
        room.Building = "BLISS";
        room.RoomNumber = "205";

        List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room , LocalDate.parse("2021-11-18"));
        assertEquals(list1.size(), 5);
    }
    @Test
    @DisplayName("Room Schedule Test14 (Number of Class thought in Nicely 212)")
    void test14() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Main.Room room = new Main.Room();
        room.Building = "NICELY";
        room.RoomNumber = "212";

        List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room);
        assertEquals(list1.size(), 15);
    }
    @Test
    @DisplayName("Prof Schedule Test15")
    void test15() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Instructor instructor = new Instructor();
        instructor.firstName = "Mahmoud";
        instructor.lastName = "Bdeir";
        List<Schedule> llist = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
        assertEquals(llist.size() , 7);
    }
    @Test
    @DisplayName("Prof Schedule Test16")
    void test16() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Instructor instructor = new Instructor();
        instructor.firstName = "Haidar";
        instructor.lastName = "Safa";
        List<Schedule> llist = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
        assertEquals(llist.size() , 9);
    }
    @Test
    @DisplayName("Prof Schedule Test17")
    void test17() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Instructor instructor = new Instructor();
        instructor.firstName = "Melhem";
        instructor.lastName = "Rahmeh";
        List<Schedule> llist = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
        assertEquals(llist.size() , 0);  //as I am not an Instructor
    }
    @Test
    @DisplayName("Prof Schedule Test18")
    void test18() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Instructor instructor = new Instructor();
        instructor.firstName = "Saeed";
        instructor.lastName = "Raheel";
        List<Schedule> llist = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
        assertEquals(llist.size() , 6);  //as I am not an Instructor
    }
    @Test
    @DisplayName("Prof Schedule Test19")
    void test19() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Instructor instructor = new Instructor();
        instructor.firstName = "Shady";
        instructor.lastName = "Elbassuoni";
        List<Schedule> llist = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
        assertEquals(llist.size() , 9);
    }
    @Test
    @DisplayName("Prof Schedule Test20")
    void test20() {
        fillList();
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        Instructor instructor = new Instructor();
        instructor.firstName = "Joe";
        instructor.lastName = "Smith";
        List<Schedule> llist = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
        assertEquals(llist.size() , 0);  //as I am not an Instructor
    }
}
