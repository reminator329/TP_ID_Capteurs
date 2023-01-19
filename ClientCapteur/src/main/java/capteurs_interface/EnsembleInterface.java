package capteurs_interface;

import java.util.Map;

public interface EnsembleInterface extends java.rmi.Remote {

    int newEnsemble() throws java.rmi.RemoteException;

    void addCapteurToEnsemble(int idEnsemble, String nameCapteur, String idCapteur) throws java.rmi.RemoteException;

    Map<String, Map<String, String>> getData(int id) throws java.rmi.RemoteException;
    Map<Integer, Map<String, Map<String, String>>> getDatas() throws java.rmi.RemoteException;

}
