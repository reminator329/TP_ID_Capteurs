package objet_interface;

import data.Data;
import listener.Listener;

import java.util.HashMap;
import java.util.Map;

public class Ensemble {

    private int identifiant;
    Map<String, Listener> capteurs;

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
        capteurs = new HashMap<>();
    }

    public void addCapteur(String id, Listener listener) {
        capteurs.put(id, listener);
    }

    public Map<String, Listener> getCapteurs() {
        return capteurs;
    }
}
