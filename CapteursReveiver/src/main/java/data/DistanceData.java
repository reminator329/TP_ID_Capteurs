package data;

import listener.DistanceListener;

import java.util.HashMap;
import java.util.Map;

public class DistanceData implements Data {

    private final Map<String, String> data;

    public static final String idDistance = "distance";

    public DistanceData() {
        this.data = new HashMap<>();
    }

    @Override
    public void setData(String idData, String value) {
        System.out.println(idData + " " + value);
        data.put(idData, value);
    }

    @Override
    public Map<String, String> getDatas() {
        return data;
    }
}
