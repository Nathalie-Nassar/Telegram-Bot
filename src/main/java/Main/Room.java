package Main;

public class Room implements Helpers.Room{
    public String Building;
    public String RoomNumber;

    @Override
    public String getBuilding() {
        return Building;
    }

    @Override
    public String getRoomNumber() {
        return RoomNumber;
    }

    public String toString() {
        return getBuilding() + " " + getRoomNumber();
    }
}
