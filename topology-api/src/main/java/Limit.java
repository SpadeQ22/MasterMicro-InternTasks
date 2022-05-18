public class Limit {
    private String type;
    private double defaultval;
    private double minVal;
    private double maxVal;

    public Limit(String type, double defaultval, double minVal, double maxVal) {
        this.type = type;
        this.defaultval = defaultval;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDefaultval() {
        return defaultval;
    }

    public void setDefaultval(double defaultval) {
        this.defaultval = defaultval;
    }

    public double getMinVal() {
        return minVal;
    }

    public void setMinVal(double minVal) {
        this.minVal = minVal;
    }

    public double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(double maxVal) {
        this.maxVal = maxVal;
    }
}
