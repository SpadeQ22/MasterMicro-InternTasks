import javax.management.openmbean.OpenDataException;
import java.io.IOException;
import java.util.ArrayList;

public class TopologyApi {

    public Topology readJson(String path) throws IOException {
        Topology top = JsonHelper.readJson(path);
        Database.database.add(top);
        return top;
    }
    public void writeJson(String topologyID, String path) throws IOException {
        Topology topo = searchTopologies(topologyID);
        if(topo != null){
            JsonHelper.writeJson(topo, path);
        } else {
            throw new IOException("No Topology Found With This id");
        }
    }
    public ArrayList<Topology> queryTopologies(){
        return Database.database;
    }
    public void deleteTopology(String topologyID) throws IOException {
        Topology topo = searchTopologies(topologyID);
        if(topo != null){
            Database.database.remove(topo);
        } else{
            throw new IOException("No Topology Found With This id");
        }
    }
    public ArrayList<Device> queryDevices(String topologyID) throws IOException {
        Topology topo = searchTopologies(topologyID);
        if(topo != null){
            return topo.getComponents();
        } else {
            throw new IOException("No Topology Found With This id");
        }
    }

    public ArrayList<Device> queryDevicesWithNetlistNode(String topologyID, String netlistNode) throws IOException {
        Topology topo = searchTopologies(topologyID);
        ArrayList<Device> devices = new ArrayList<>();
        if(topo != null){
            for(Device dev: topo.getComponents()){
                if(dev.getNetlist().containsValue(netlistNode)){
                    devices.add(dev);
                }
            }
            return devices;
        } else {
            throw new IOException("No Topology Found With This id");
        }
    }
    private Topology searchTopologies(String topologyID){
        for(Topology top: Database.database){
            if(top.getId().equals(topologyID)){
                return top;
            }
        }
        return null;
    }




}
