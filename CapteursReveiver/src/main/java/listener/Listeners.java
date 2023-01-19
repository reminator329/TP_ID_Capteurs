package listener;

import data.Data;
import data.Datas;
import data.MeteoData;

public enum Listeners {

    METEO("meteo", new MeteoListener()),
    DISTANCE("distance", new DistanceListener())
    ;

    String name;
    Listener listener;

    Listeners(String name, Listener listener) {
        this.name = name;
        this.listener = listener;
    }

    public static Listener getListenerByName(String name) {
        for (Listeners listeners : values()) {
            if (listeners.name.equals(name)) {
                return listeners.listener;
            }
        }
        return null;
    }
}
