package data;

import java.util.HashMap;
import java.util.Map;

public class MeteoData implements Data {

    private final Map<String, String> data;

    public static final String idTemperature = "temperature";

    public MeteoData() {
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
