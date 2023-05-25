package Main;

public class Instructor implements Helpers.Instructor{
    public String firstName;
    public String lastName;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
