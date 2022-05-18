import java.util.HashMap;

public class Device {
    private String id;
    private String type;
    private Limit limit;
    private HashMap<String, String> netlist;

    public Device(String id, String type, Limit limit, HashMap<String, String> netlist) {
        this.id = id;
        this.type = type;
        this.limit = limit;
        this.netlist = netlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public HashMap<String, String> getNetlist() {
        return netlist;
    }

    public void setNetlist(HashMap<String, String> netlist) {
        this.netlist = netlist;
    }
}
