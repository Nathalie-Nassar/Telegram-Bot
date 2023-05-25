package Main;

import Helpers.DayOfWeek;
import Helpers.Schedule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class HW4 {
    static CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
    static Scanner console = new Scanner(System.in);
    static String input = console.nextLine().trim();
    static String[] elements = input.split(" +");
    static List<String> list = Arrays.asList(elements);
    static Room room = new Room();
    static Instructor instructor = new Instructor();
    static List<Helpers.Course> courseList = new LinkedList<>();
    static List<Schedule> scheduleList;
    static List<String> dayOfTheWeeks = Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");

    static void roomSchedule() {
        if (list.get(2).startsWith("\"") && list.get(3).endsWith("\"")) {
            room.Building = list.get(2).substring(1).toUpperCase();
            room.RoomNumber = list.get(3).substring(0, list.get(3).length() - 1);
            scheduleList = courSeeraFactory.createInstance(courseList).roomSchedule(room);
            for (Schedule schedule : scheduleList) {
                System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
            }
        }
    }

    static void roomScheduleDay() {
        DayOfWeek day = switch (list.get(4).toUpperCase()) {
            case "MONDAY" -> DayOfWeek.Monday;
            case "TUESDAY" -> DayOfWeek.Tuesday;
            case "WEDNESDAY" -> DayOfWeek.Wednesday;
            case "THURSDAY" -> DayOfWeek.Thursday;
            case "FRIDAY" -> DayOfWeek.Friday;
            case "SATURDAY" -> DayOfWeek.Saturday;
            default -> throw new IllegalStateException("Unexpected value: " + list.get(4).toUpperCase());
        };
        scheduleList = courSeeraFactory.createInstance(courseList).roomSchedule(room, day);
        for (Schedule schedule : scheduleList) {
            System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
        }
    }

    static void roomScheduleTime() {
        LocalDate date = LocalDate.parse(list.get(4));
        scheduleList = courSeeraFactory.createInstance(courseList).roomSchedule(room, date);
        for (Schedule schedule : scheduleList) {
            System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
        }
    }

    static void whoWasThereLast() {
        if (list.get(2).startsWith("\"") && list.get(3).endsWith("\"")) {
            room.Building = list.get(2).substring(1).toUpperCase();
            room.RoomNumber = list.get(3).substring(0, list.get(3).length() - 1);
            Schedule schedule = courSeeraFactory.createInstance(courseList).whoWasThereLast(room);
            System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
        }
    }

    static void whoIsThereNow() {
        if (list.get(2).startsWith("\"") && list.get(3).endsWith("\"")) {
            room.Building = list.get(2).substring(1).toUpperCase();
            room.RoomNumber = list.get(3).substring(0, list.get(3).length() - 1);
            Schedule schedule = courSeeraFactory.createInstance(courseList).whoIsThereNow(room);
            System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
        }
    }

    static void profSchedule() {
        if (list.get(2).startsWith("\"") && list.get(3).endsWith("\"")) {
            instructor.firstName = list.get(2).substring(1);
            instructor.lastName = list.get(3).substring(0, list.get(3).length() - 1);
            scheduleList = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
            for (Schedule schedule : scheduleList) {
                System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
            }
        }
    }

    static void whereIsProf() {
        if (list.get(2).startsWith("\"") && list.get(3).endsWith("\"")) {
            instructor.firstName = list.get(2).substring(1);
            instructor.lastName = list.get(3).substring(0, list.get(3).length() - 1);
            Schedule schedule = courSeeraFactory.createInstance(courseList).whereIsProf(instructor);
            System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
        }
    }

    static void whereWillProfBe() {
        if (list.get(2).startsWith("\"") && list.get(3).endsWith("\"")) {
            instructor.firstName = list.get(2).substring(1);
            instructor.lastName = list.get(3).substring(0, list.get(3).length() - 1);
            scheduleList = courSeeraFactory.createInstance(courseList).whereWillProfBe(instructor);
            for (Schedule schedule : scheduleList) {
                System.out.println(schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , " + schedule.getInstructor());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        for (char c = 'a'; c <= 'z'; c++) {
            try {
                new CsvToDb().csvToDb(courseList, new File("C:\\Users\\student\\Desktop\\Bot\\src\\main\\java\\Main\\CSVFiles\\" + c + ".csv").getAbsolutePath());
            } catch (Exception e) {}
        }
        if (list.get(0).toLowerCase().equals("courseera")) {
            if (list.size() == 2) {
                if (list.get(1).equals("roomschedule")) {
                    System.out.println(courSeeraFactory.createInstance(courseList).roomSchedule());
                }
            } else if (list.get(1).equals("roomschedule")) {
                if (list.size() == 4) {
                    roomSchedule();
                }
                if (list.size() == 5) {
                    if (list.get(2).startsWith("\"") && list.get(3).endsWith("\"")) {
                        room.Building = list.get(2).substring(1).toUpperCase();
                        room.RoomNumber = list.get(3).substring(0, list.get(3).length() - 1);
                        if (dayOfTheWeeks.contains(list.get(4).toUpperCase())) {
                            roomScheduleDay();
                        } else {
                            roomScheduleTime();
                        }
                    }
                }
            } else if (list.get(1).equals("whowastherelast")) {
                whoWasThereLast();
            } else if (list.get(1).equals("whoistherenow")) {
                whoIsThereNow();
            } else if (list.get(1).equals("profSchedule")) {
                profSchedule();
            } else if (list.get(1).equals("whereIsProf")) {
                whereIsProf();
            } else if (list.get(1).equals("whereWillProfBe")) {
                whereWillProfBe();
            }
        } else {
            System.out.println("Input Error");
        }
    }
}