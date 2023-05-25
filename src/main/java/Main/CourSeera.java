package Main;

import Helpers.Room;
import Helpers.Course;
import Helpers.Schedule;
import Helpers.Instructor;
import Helpers.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
public class CourSeera implements Helpers.CourSeera{
    List<Course> courses;
    LocalTime time = LocalTime.now();
    LocalDate date = LocalDate.now();
    public static class RoomComparator implements Comparator<Room> {
        @Override
        public int compare(Room room1, Room room2) {
            return (room1.getBuilding()+" " +room1.getRoomNumber()).compareTo(room2.getBuilding()+" "+room2.getRoomNumber());
        }
    }

    public static class ScheduleComparator implements Comparator<Schedule> {
        @Override
        public int compare(Schedule schedule1, Schedule schedule2) {
            if (schedule1.getFromTime().isBefore(schedule2.getFromTime())){
                return  -1;
            }
            else if (schedule1.getFromTime().equals(schedule2.getFromTime())) {
                return  0;
            }
            else {
                return  1;
            }
        }
    }

    @Override
    public TreeMap<Room, List<Schedule>> roomSchedule() {
        List<Course> courseList = new LinkedList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            try {
                CsvToDb object = new CsvToDb();
                File csvFile = new File("C:\\Users\\student\\Desktop\\Bot\\src\\main\\java\\Main\\CSVFiles\\" + c + ".csv");
                String csv = csvFile.getAbsolutePath();
                object.csvToDb(courseList, csv);
            } catch (Exception e) {
            }
        }

        RoomComparator comparator = new RoomComparator();
        TreeMap<Room, List<Schedule>> roomschedule = new TreeMap<>(comparator);
        List<Main.Room> rooms = new ArrayList<>();
        for (Course course : courseList) {
            Main.Room room = new Main.Room();
            room.Building = course.getBldg();
            room.RoomNumber = course.getRoom();
            if (!room.toString().equals(". .") && !rooms.contains(room)) rooms.add(room);
        }
        for(Room room : rooms){
            List<Schedule> list = new ArrayList<>();
            for (Course course : courseList) {
                if (course.getBldg().equals(room.getBuilding()) && course.getRoom().equals(room.getRoomNumber())) {
                    String days = "";
                    Main.Schedule schedule = new Main.Schedule();
                    schedule.Course = course.getSubject() + " " + course.getCourse_num();
                    schedule.FromTime = course.getBegin_time();
                    schedule.toTime = course.getEnd_time();
                    schedule.Room = course.getBldg() + " " + course.getRoom();
                    schedule.Instructor = course.getInstructor_first() + " " + course.getInstructor_last();
                    if (course.getMonday()) days +=" Monday ";
                    if (course.getTuesday()) days +=" Tuesday ";
                    if (course.getWednesday()) days +=" Wednesday ";
                    if (course.getThursday()) days +=" Thursday ";
                    if (course.getFriday()) days +=" Friday ";
                    if (course.getSaturday()) days +=" Saturday ";
                    schedule.Day = days;

                    list.add(schedule);
                }
            }
            roomschedule.put(room , list );
        }
        return roomschedule;
    }

    @Override
    public List<Schedule> roomSchedule(Room room) {
        TreeMap<Room , List<Schedule>> treeMap = roomSchedule();
        List<Schedule> scheduleList = new ArrayList<>();
        for (Room r:treeMap.keySet()) {
            if (r.getBuilding().equals(room.getBuilding()) && r.getRoomNumber().equals(room.getRoomNumber())) {
                scheduleList =  treeMap.get(r);
            }
        }
        ScheduleComparator comparator = new ScheduleComparator();
        scheduleList.sort(comparator);
        return scheduleList;
    }

    @Override
    public List<Schedule> roomSchedule(Room room, LocalDate date) {
        List<Schedule> scheduleList = new LinkedList<>();
        String dayToString = Character.toUpperCase(date.getDayOfWeek().toString().charAt(0)) + date.getDayOfWeek().toString().substring(1).toLowerCase(Locale.ROOT);

        for (Course course : courses) {
            boolean state = switch (dayToString) {
                case "Monday" -> course.getMonday();
                case "Tuesday" -> course.getTuesday();
                case "Wednesday" -> course.getWednesday();
                case "Thursday" -> course.getThursday();
                case "Friday" -> course.getFriday();
                case "Saturday" -> course.getSaturday();
                default -> false;
            };

            if (course.getBldg().equals(room.getBuilding()) && course.getRoom().equals(room.getRoomNumber()) && state) {
                String days = "";
                Main.Schedule schedule = new Main.Schedule();
                schedule.Course = course.getSubject() + " " + course.getCourse_num();
                schedule.FromTime = course.getBegin_time();
                schedule.toTime = course.getEnd_time();
                schedule.Room = course.getBldg() + " " + course.getRoom();
                schedule.Instructor = course.getInstructor_first() + " " + course.getInstructor_last();
                if (course.getMonday()) days +=" Monday ";
                if (course.getTuesday()) days +=" Tuesday ";
                if (course.getWednesday()) days +=" Wednesday ";
                if (course.getThursday()) days +=" Thursday ";
                if (course.getFriday()) days +=" Friday ";
                if (course.getSaturday()) days +=" Saturday ";
                schedule.Day = days;
                scheduleList.add(schedule);
            }
        }
        ScheduleComparator comparator = new ScheduleComparator();
        scheduleList.sort(comparator);
        return scheduleList;
    }

    @Override
    public List<Schedule> roomSchedule(Room room, DayOfWeek day) {
        List<Schedule> scheduleList = new LinkedList<>();
        String dayStr = day.toString();
        for (Course course : courses) {
            boolean state = switch (dayStr) {
                case "Monday" -> course.getMonday();
                case "Tuesday" -> course.getTuesday();
                case "Wednesday" -> course.getWednesday();
                case "Thursday" -> course.getThursday();
                case "Friday" -> course.getFriday();
                case "Saturday" -> course.getSaturday();
                default -> false;
            };


            if (course.getBldg().equals(room.getBuilding()) && course.getRoom().equals(room.getRoomNumber()) && state) {
                String days = "";
                Main.Schedule schedule = new Main.Schedule();
                schedule.Course = course.getSubject() + " " + course.getCourse_num();
                schedule.FromTime = course.getBegin_time();
                schedule.toTime = course.getEnd_time();
                schedule.Room = course.getBldg() + " " + course.getRoom();
                schedule.Instructor = course.getInstructor_first() + " " + course.getInstructor_last();
                if (course.getMonday()) days +=" Monday ";
                if (course.getTuesday()) days +=" Tuesday ";
                if (course.getWednesday()) days +=" Wednesday ";
                if (course.getThursday()) days +=" Thursday ";
                if (course.getFriday()) days +=" Friday ";
                if (course.getSaturday()) days +=" Saturday ";
                schedule.Day = days;
                scheduleList.add(schedule);
            }
        }
        ScheduleComparator comparator = new ScheduleComparator();
        scheduleList.sort(comparator);
        return scheduleList;
    }

    @Override
    public Schedule whoWasThereLast(Room room) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        List<Schedule> list = roomSchedule(room, date);
        LocalTime MaxEndtime = LocalTime.parse("00:00");
        Schedule whoWasThereLast = new Main.Schedule();
        for (Schedule schedule : list) {
            if (schedule.getToTime().isAfter(MaxEndtime) && schedule.getToTime().isBefore(time)) {
                MaxEndtime = schedule.getToTime();
                whoWasThereLast = schedule;
            }
        }
        return whoWasThereLast;
    }

    @Override
    public Schedule whoIsThereNow(Room room) {
        List<Schedule> list = roomSchedule(room, date);
        Schedule whoIsThereNow = new Main.Schedule();
        for (Schedule schedule : list) {
            if ((schedule.getToTime().isAfter(time) || schedule.getToTime().equals(time)) && (schedule.getFromTime().isBefore(time)) || schedule.getFromTime().equals(time)) {
                whoIsThereNow = schedule;
            }
        }
        return whoIsThereNow;
    }

    @Override
    public List<Schedule> profSchedule(Instructor instructor) {
        String days = "";
        List<Schedule> scheduleList = new LinkedList<>();
        for (Course course : courses) {
            if (course.getInstructor_first().equals(instructor.getFirstName()) && course.getInstructor_last().equals(instructor.getLastName())) {
                Main.Schedule schedule = new Main.Schedule();
                schedule.Course = course.getSubject() + " " + course.getCourse_num();
                schedule.FromTime = course.getBegin_time();
                schedule.toTime = course.getEnd_time();
                schedule.Room = course.getBldg() + " " + course.getRoom();
                schedule.Instructor = course.getInstructor_first() + " " + course.getInstructor_last();
                if (course.getMonday()) days += " Monday ";
                if (course.getTuesday()) days += " Tuesday ";
                if (course.getWednesday()) days += " Wednesday ";
                if (course.getThursday()) days += " Thursday ";
                if (course.getFriday()) days += " Friday ";
                if (course.getSaturday()) days += " Saturday ";
                schedule.Day = days;
                scheduleList.add(schedule);
            }
        }
        return scheduleList;
    }

    @Override
    public Schedule whereIsProf(Instructor instructor) {
        Schedule whereIsProf = new Main.Schedule();
        try{
            LocalTime time = LocalTime.now();
            List<Schedule> scheduleList = profSchedule(instructor);
            for (Schedule schedule : scheduleList) {
                if ((schedule.getToTime().isAfter(time) || schedule.getToTime().equals(time)) && (schedule.getFromTime().isBefore(time) || schedule.getFromTime().equals(time)) && schedule.getFromTime() != null && schedule.getToTime() != null) {
                    whereIsProf = schedule;
                }
            }}
        catch (Exception e) {}
        return whereIsProf;
    }

    @Override
    public List<Schedule> whereWillProfBe(Instructor instructor) {
        List<Schedule> scheduleList = new LinkedList<>();
        List<Schedule> profschedule = profSchedule(instructor);
        for (Schedule sc : profschedule) {
            if (sc.getDay().contains(Character.toTitleCase(LocalDate.now().getDayOfWeek().toString().charAt(0)) + LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase())){
                scheduleList.add(sc);
            }
        }
        return scheduleList;
    }
}
