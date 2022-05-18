import java.util.ArrayList;

public class Squad {
    private int squadSize;
    private  String squadName;
    private String squadCause;
    private int squadId;
    private static ArrayList<Squad> instances = new ArrayList<Squad>();

    public Squad( String squadName, String squadCause) {
        this.squadSize = 4;
        this.squadName = squadName;
        this.squadCause = squadCause;
        this.squadId = squadId;
        this.instances.add(this);
    }

    public int getSquadSize() {
        return squadSize;
    }

    public void setSquadSize(int squadSize) {
        this.squadSize = squadSize;
    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public String getSquadCause() {
        return squadCause;
    }

    public void setSquadCause(String squadCause) {
        this.squadCause = squadCause;
    }

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

    public static ArrayList<Squad> getAll() {
        return instances;
    }


}
