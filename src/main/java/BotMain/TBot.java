package BotMain;

import Helpers.Course;
import Helpers.DayOfWeek;
import Helpers.Room;
import Helpers.Schedule;
import Main.CourSeeraFactory;
import Main.CsvToDb;
import Main.Instructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class TBot extends TelegramLongPollingBot {
    String[] botFunctions = new String[]{"allroomschedule", "roomschedule", "roomscheduletime", "roomscheduleday", "whowastherelast",  "whoistherenow" ,"profschedule", "whereisprof", "wherewillprofbe"};
    CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
    List<Course> courseList = new LinkedList<>();

    public void onUpdateReceived(Update update) {
        if (courseList.isEmpty()) {
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
// if there is a message that has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // get the text of the message
            String receivedText = update.getMessage().getText();
            SendMessage message = new SendMessage();
            // send a reply
            String output = generatedReply(receivedText);
            if (receivedText.substring(1).equals(botFunctions[0])){
                int len = output.length()/4096+1;
                for (int j = 0; j < len ; j++) {
                    //SendMessage message = new SendMessage();
                    message.setChatId(update.getMessage().getChatId().toString());
                    String str = output.substring(j*4096 , (j+1)*4096);
                    message.setText(str);
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                //SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText(output);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String getBotUsername() {
        return "nathalie_melhem_telegram_bot";
    }

    @Override
    public String getBotToken() {
        return "2143994897:AAFTWYbYpnSt-CWU49_nsLHCpLIJ-G38azk";
    }

    public String generatedReply(String receivedText) {
        /*
         * STEPS:
         * 1. Parse the received text: ignore '/' - get the fct name and the parameters(if any)
         * 2. Map the fct name to the appropriate fct in CourSeera and pass to it the pars(if any)
         * 3. Return the string which represents the reply
         * */

        return ParsedString(receivedText);  // ignoring the '/'

    }


    public String ParsedString(String receivedText) {
        String temp = receivedText;
        receivedText = receivedText.substring(1);
        CourSeeraFactory courSeeraFactory = new CourSeeraFactory();
        List<String> dayOfTheWeeks = Arrays.asList("Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday" ,"Saturday");
        Main.Room room = new Main.Room();
        Instructor instructor = new Instructor();
        String output = "";
        String[] st = receivedText.split(" +");
        String function = st[0]; // get the function name of the bot
        // use switch to call on the appropriate CourSeera function
        switch (Arrays.asList(botFunctions).indexOf(function)) {
            case 0:  // allroomschedule = roomSchedule : lists the schedule for every room in alphabetical room number order
                TreeMap<Room, List<Schedule>> map = courSeeraFactory.createInstance(courseList).roomSchedule();
                String str = "";
                for (Room room1 : map.keySet()) {
                    str += room1 + " :" + map.get(room1)+'\n';
                }
                output = str;
                return output;
            case 1: //roomschedule = roomSchedule(Room room) : lists the schedule for a specific room
                room.Building = st[1].trim().toUpperCase();
                room.RoomNumber = st[2].trim();
                List<Schedule> list1 = courSeeraFactory.createInstance(courseList).roomSchedule(room);
                String roomschedule = "";
                for (int i = 0; i < dayOfTheWeeks.size(); i++) {
                    for (Schedule schedule : list1) {
                        if (schedule.getDay().contains(dayOfTheWeeks.get(i))){
                            roomschedule += dayOfTheWeeks.get(i)+ ":  " +schedule.getFromTime() + " - " + schedule.getToTime() + "  " + schedule.getCourse() + "  ,  " + schedule.getInstructor() + '\n';
                        }
                    }
                    roomschedule+='\n';
                }
                return roomschedule;
            case 2: // roomscheduletime =  roomSchedule(Room room, java.time.LocalDate date) : lists the schedule for a specific room for a specific date
                LocalDate date = LocalDate.parse(st[3]);
                room.Building = st[1].trim().toUpperCase();
                room.RoomNumber = st[2].trim();
                List<Schedule> list = courSeeraFactory.createInstance(courseList).roomSchedule(room , date);
                String roomscheduletime = "";
                String dayStr = date.getDayOfWeek().toString();
                for (Schedule schedule : list) {
                    roomscheduletime += Character.toUpperCase(dayStr.charAt(0))+ dayStr.substring(1).toLowerCase(Locale.ROOT)+":  " +schedule.getFromTime() + " - " + schedule.getToTime() + "  " + schedule.getCourse() + "  ,  " + schedule.getInstructor() + '\n';
                }
                return roomscheduletime;
            case 3:// roomscheduleday = roomSchedule(Room room, DayOfWeek day) : lists the schedule for a specific room for a specific day of week
                room.Building = st[1].trim().toUpperCase();
                room.RoomNumber = st[2].trim();
                String roomscheduleday = "";
                System.out.println(Character.toUpperCase(st[3].charAt(0))+st[3].substring(1).toLowerCase());
                if (dayOfTheWeeks.contains(Character.toUpperCase(st[3].charAt(0))+st[3].substring(1).toLowerCase())){
                    DayOfWeek day = switch (Character.toUpperCase(st[3].charAt(0))+st[3].substring(1).toLowerCase()) {
                        case "Monday" -> DayOfWeek.Monday;
                        case "Tuesday" -> DayOfWeek.Tuesday;
                        case "Wednesday" -> DayOfWeek.Wednesday;
                        case "Thursday" -> DayOfWeek.Thursday;
                        case "Friday" -> DayOfWeek.Friday;
                        case "Saturday" -> DayOfWeek.Saturday;
                        default -> throw new IllegalStateException("Unexpected value: " + st[3]);
                    };

                    List<Schedule> list2 = courSeeraFactory.createInstance(courseList).roomSchedule(room , day);
                    for (Schedule schedule : list2) {
                        roomscheduleday += day.toString()+ ":  " +schedule.getFromTime() + " - " + schedule.getToTime() + "  " + schedule.getCourse() + "  ,  " + schedule.getInstructor() + '\n';
                    }
                    output = roomscheduleday;
                }
                return output;

            case 4: // whowastherelast = whoWasThereLast(Room room) : lists the course and instructor name for the last time this room was occupied
                room.Building = st[1].trim().toUpperCase();
                room.RoomNumber = st[2].trim();
                Schedule schedule = courSeeraFactory.createInstance(courseList).whoWasThereLast(room);
                return (LocalDate.now().getDayOfWeek().toString().charAt(0)+LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase(Locale.ROOT) + ": " +schedule.getFromTime() + "-" + schedule.getToTime() + " " + schedule.getCourse() + " , "+ schedule.getInstructor());
            case 5:  // whoistherenow = whoIsThereNow(Room room) : lists the course and instructor name currently occupying a specific room
                room.Building = st[1].trim().toUpperCase();
                room.RoomNumber = st[2].trim();
                Schedule nowSchedule = courSeeraFactory.createInstance(courseList).whoIsThereNow(room);
                return (LocalDate.now().getDayOfWeek().toString().charAt(0)+LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase(Locale.ROOT) + ": " +nowSchedule.getFromTime() + "-" + nowSchedule.getToTime() + " " + nowSchedule.getCourse() + " , "+ nowSchedule.getInstructor());
            case 6: // profschedule = profSchedule(Instructor instructor) : lists the instructor’s weekly schedule (day, time, room)
                instructor.firstName = Character.toUpperCase(st[1].trim().charAt(0))+st[1].trim().substring(1).toLowerCase();
                instructor.lastName = Character.toUpperCase(st[2].trim().charAt(0))+st[2].trim().substring(1).toLowerCase();
                String profsched = "";
                List<Schedule> llist = courSeeraFactory.createInstance(courseList).profSchedule(instructor);
                for (int i = 0; i < dayOfTheWeeks.size(); i++) {
                    for (Schedule profschedule : llist) {
                        if (profschedule.getDay().contains(dayOfTheWeeks.get(i))){
                            profsched += dayOfTheWeeks.get(i)+ ":  " +profschedule.getFromTime() + " - " + profschedule.getToTime() + "  " + profschedule.getCourse() + "  ,  " + profschedule.getInstructor() + '\n';
                        }
                    }
                    profsched+='\n';
                }
                return profsched;
            case 7: // whereisprof = whereIsProf(Instructor instructor) : lists the room where a prof is currently teaching (if any)
                instructor.firstName = Character.toUpperCase(st[1].trim().charAt(0))+st[1].trim().substring(1).toLowerCase();
                instructor.lastName = Character.toUpperCase(st[2].trim().charAt(0))+st[2].trim().substring(1).toLowerCase();
                Schedule whereisprof = courSeeraFactory.createInstance(courseList).whereIsProf(instructor);
                if (whereisprof.getDay().contains(LocalDate.now().getDayOfWeek().toString().charAt(0)+LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase(Locale.ROOT))){
                    return (LocalDate.now().getDayOfWeek().toString().charAt(0)+LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase(Locale.ROOT) + ": " +whereisprof.getFromTime() + "-" + whereisprof.getToTime() + " " + whereisprof.getCourse() + " , "+ whereisprof.getInstructor());
                }
                else{
                    return "No schedule for "+ instructor.firstName +" "+ instructor.lastName + " for today";
                }
            case 8: // wherewillprofbe = whereWillProfBe(Instructor instructor) : lists the instructor’s schedule for today
                instructor.firstName = Character.toUpperCase(st[1].trim().charAt(0))+st[1].trim().substring(1).toLowerCase();
                instructor.lastName = Character.toUpperCase(st[2].trim().charAt(0))+st[2].trim().substring(1).toLowerCase();
                List<Schedule> wherewillprofbe = courSeeraFactory.createInstance(courseList).whereWillProfBe(instructor);
                String wherewillprofbeStr = "";
                String d = Character.toTitleCase(LocalDate.now().getDayOfWeek().toString().charAt(0)) + LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase();
                for (Schedule s:wherewillprofbe) {
                    if (s.getDay().contains(LocalDate.now().getDayOfWeek().toString().charAt(0)+LocalDate.now().getDayOfWeek().toString().substring(1).toLowerCase(Locale.ROOT))){
                        wherewillprofbeStr+=(d+" :  "+s.getFromTime() + "-" + s.getToTime() + " " + s.getCourse() + " , "+ s.getInstructor() + '\n');
                    }
                }
                return wherewillprofbeStr;
            default:
                return ("I received: "+ temp);
        }
    }
}