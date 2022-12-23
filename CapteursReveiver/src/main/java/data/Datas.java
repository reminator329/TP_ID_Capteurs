package data;

public enum Datas {

    METEO("meteo", new MeteoData()),
    ;

    String name;
    Data data;

    Datas(String name, Data data) {
        this.name = name;
        this.data = data;
    }

    public static Data getDataByName(String name) {
        for (Datas datas : values()) {
            if (datas.name.equals(name)) {
                return datas.data;
            }
        }
        return null;
    }
}
