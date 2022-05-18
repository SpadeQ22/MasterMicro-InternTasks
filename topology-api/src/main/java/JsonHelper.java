import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonHelper {
    public static void main(String[] args) throws IOException {
        Topology t = readJson("topology.json");
        Database.database.add(t);
    }
    static Topology readJson(String path) throws IOException {
        String jsonData = new String(Files.readAllBytes(Path.of("D:\\Programming\\Interview-Tasks\\topology-api\\src\\main\\java\\topology.json")));
        Map<String, Object> jsonTop = new Gson().fromJson(jsonData, Map.class);
        String id = (String) jsonTop.get("id");
        var jsonDevices = new Gson().fromJson(jsonTop.get("components").toString(), ArrayList.class);
        ArrayList<Device> components = new ArrayList<>();
        for(var jsonDevice: jsonDevices){
            components.add(JsonHelper.getComponentfromJson(jsonDevice.toString()));
        }
        return new Topology(id, components);
    }
    static void writeJson(Topology topology, String path) throws IOException {
        String JsonData = getTopologyJson(topology);
        new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(JsonData);
        writer.close();
    }
    private static Device getComponentfromJson(String jsonDevice){
        String type = null, id = null;
        HashMap<String, String> netlist = null;
        Limit limit = null;
        Map<String, Object> deviceData = new Gson().fromJson(jsonDevice, Map.class);
        for(String key: deviceData.keySet()){
            if(key.equals("type")){
                type = (String) deviceData.get(key);
            } else if(key.equals("id")){
                id = (String) deviceData.get(key);
            } else if(key.equals("netlist")){
                netlist = getNetListfromJson(deviceData.get(key).toString());
            } else {
                limit = getLimitfromJson(deviceData.get(key).toString(), key);
            }
        }
        return new Device(id, type, limit, netlist);
    }
    private static HashMap<String, String> getNetListfromJson(String netlistJson){
        Map<String, Object> netlistData = new Gson().fromJson(netlistJson, Map.class);
        HashMap<String, String> netlist = new HashMap<>();
        for(String key: netlist.keySet()){
            netlist.put(key,(String) netlistData.get(key));
        }
        return netlist;
    }
    private static Limit getLimitfromJson(String limitJson, String type){
        Map<String, Object> limitData = new Gson().fromJson(limitJson, Map.class);
        double defaultVal = 0;
        double minVal = 0;
        double maxVal = 0;
        for(String key: limitData.keySet()){
            if(key.equals("default")){
                defaultVal =(Double) limitData.get(key);
            } else if(key.equals("min")){
                minVal = (Double) limitData.get(key);
            } else if(key.equals("max")){
                maxVal = (Double) limitData.get(key);
            }
        }
        return new Limit(type, defaultVal, minVal, maxVal);
    }
    private static String getTopologyJson(Topology topology){
        HashMap<String, Object> jsonData = new HashMap<>();
        String id = topology.getId();
        jsonData.put("id", id);
        jsonData.put("components", getJsonComponents(topology.getComponents()));
        return new GsonBuilder().setPrettyPrinting().create().toJson(jsonData);
    }
    private static ArrayList<HashMap<String, Object>> getJsonComponents(ArrayList<Device> components){
        ArrayList<HashMap<String,Object>> componentsMap = new ArrayList<>();
        for(Device comp: components){
            HashMap<String, Object> limitMap = new HashMap<>();
            HashMap<String, Object> deviceMap = new HashMap<>();
            Limit limit = comp.getLimit();
            limitMap.put("default", limit.getDefaultval());
            limitMap.put("min", limit.getMinVal());
            limitMap.put("max", limit.getMaxVal());
            deviceMap.put("id", comp.getId());
            deviceMap.put("type", comp.getType());
            deviceMap.put("netlist",comp.getNetlist());
            deviceMap.put(limit.getType(),limitMap);
            componentsMap.add(deviceMap);
        }
        return componentsMap;

    }

}
