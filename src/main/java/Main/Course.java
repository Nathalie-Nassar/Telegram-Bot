package Main;

import java.time.LocalTime;

public class Course implements Helpers.Course{
    String Crn;
    String Subject;
    String CourseNumber;
    String Section;
    String Title;
    float CreditsHours;
    String College;
    int ActualEnrollement;
    int SeatsAvailable;
    LocalTime beginTime;
    LocalTime endTime;
    String Building;
    String Room;
    boolean Monday;
    boolean Tuesday;
    boolean Wednesday;
    boolean Thursday;
    boolean Friday;
    boolean Saturday;
    String InstructorFirstName;
    String InstructorLastName;

    @Override
    public String getCrn() {
        return Crn;
    }

    @Override
    public String getSubject() {
        return Subject;
    }

    @Override
    public String getCourse_num() {
        return CourseNumber;
    }

    @Override
    public String getSection() {
        return Section;
    }

    @Override
    public String getTitle() {
        return Title;
    }

    @Override
    public float getCredithrs() {
        return CreditsHours;
    }

    @Override
    public String getCollege() {
        return College;
    }

    @Override
    public int getActual_enrol() {
        return ActualEnrollement;
    }

    @Override
    public int getSeats_available() {
        return SeatsAvailable;
    }

    @Override
    public LocalTime getBegin_time() {
        return beginTime;
    }

    @Override
    public LocalTime getEnd_time() {
        return endTime;
    }

    @Override
    public String getBldg() {
        return Building;
    }

    @Override
    public String getRoom() {
        return Room;
    }

    @Override
    public boolean getMonday() {
        return Monday;
    }

    @Override
    public boolean getTuesday() {
        return Tuesday;
    }

    @Override
    public boolean getWednesday() {
        return Wednesday;
    }

    @Override
    public boolean getThursday() {
        return Thursday;
    }

    @Override
    public boolean getFriday() {
        return Friday;
    }

    @Override
    public boolean getSaturday() {
        return Saturday;
    }

    @Override
    public String getInstructor_first() {
        return InstructorFirstName;
    }

    @Override
    public String getInstructor_last() {
        return InstructorLastName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Crn='" + Crn + '\'' +
                ", Subject='" + Subject + '\'' +
                ", CourseNumber='" + CourseNumber + '\'' +
                ", Section='" + Section + '\'' +
                ", Title='" + Title + '\'' +
                ", CreditsHours=" + CreditsHours +
                ", College='" + College + '\'' +
                ", ActualEnrollement=" + ActualEnrollement +
                ", SeatsAvailable=" + SeatsAvailable +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", Building='" + Building + '\'' +
                ", Room='" + Room + '\'' +
                ", Monday=" + Monday +
                ", Tuesday=" + Tuesday +
                ", Wednesday=" + Wednesday +
                ", Thursday=" + Thursday +
                ", Friday=" + Friday +
                ", Saturday=" + Saturday +
                ", InstructorFirstName='" + InstructorFirstName + '\'' +
                ", InstructorLastName='" + InstructorLastName + '\'' +
                '}';
    }
}