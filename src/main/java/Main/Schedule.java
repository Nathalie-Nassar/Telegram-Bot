package Main;

import java.time.LocalTime;

public class Schedule implements  Helpers.Schedule{
    String Room;
    LocalTime FromTime;
    LocalTime toTime;
    String Instructor;
    String Course;
    String Day;

    @Override
    public String getRoom() {
        return Room;
    }

    @Override
    public LocalTime getFromTime() {
        return FromTime;
    } //optional    Optional<LocalTime>

    @Override
    public LocalTime getToTime() {
        return toTime;
    } //optional

    @Override
    public String getInstructor() {
        return Instructor;
    }

    @Override
    public String getCourse() {
        return Course;
    }

    @Override
    public String getDay() {
        return Day;
    }
    public String toString() {
        return getFromTime() + " - " + getToTime() + "  " + getCourse() + "  ,  " + getInstructor() + '\n';
    }
}
