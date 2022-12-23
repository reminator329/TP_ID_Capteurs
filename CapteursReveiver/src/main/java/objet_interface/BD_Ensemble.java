package objet_interface;

import capteurs_interface.EnsembleInterface;
import data.Data;
import data.Datas;
import listener.Listener;
import listener.Listeners;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BD_Ensemble extends UnicastRemoteObject implements EnsembleInterface, Serializable {

    private final Map<Integer, Ensemble> ensembles = new HashMap<>();
    private int identifiant = 0;

    public BD_Ensemble() throws RemoteException {
    }

    @Override
    public int newEnsemble() throws RemoteException {
        Ensemble ensemble = new Ensemble();
        ensemble.setIdentifiant(identifiant);
        ensembles.put(identifiant, ensemble);
        identifiant++;
        return identifiant - 1;
    }

    @Override
    public void addCapteurToEnsemble(int idEnsemble, String nameCapteur, String idCapteur) throws RemoteException {
        Ensemble ensemble = ensembles.get(idEnsemble);
        ensemble.addCapteur(idCapteur, Listeners.getListenerByName(nameCapteur));
    }

    @Override
    public Map<String, Map<String, String>> getData(int id) throws RemoteException {
        Ensemble ensemble = ensembles.get(id);
        Map<String, Listener> capteurs = ensemble.getCapteurs();
        Map<String, Map<String, String>> res = new HashMap<>();
        capteurs.forEach((idData, data) -> {
            res.put(idData, data.getDatas());
        });
        return res;
    }

    @Override
    public Map<Integer, Map<String, Map<String, String>>> getDatas() throws RemoteException {
        Map<Integer, Map<String, Map<String, String>>> res = new HashMap<>();
        ensembles.forEach((idEnsemble, ensemble) -> {
            Map<String, Listener> capteurs = ensemble.getCapteurs();
            Map<String, Map<String, String>> res2 = new HashMap<>();
            capteurs.forEach((idData, data) -> {
                res2.put(idData, data.getDatas());
            });
            res.put(idEnsemble, res2);
        });

        return res;
    }
}
