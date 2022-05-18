import java.util.ArrayList;

public class Topology {
    private String id;
    private ArrayList<Device> components = new ArrayList<>();

    public Topology(String id, ArrayList<Device> components) {
        this.id = id;
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Device> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Device> components) {
        this.components = components;
    }
}
