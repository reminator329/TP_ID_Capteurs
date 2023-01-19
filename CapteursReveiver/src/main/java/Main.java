import capteurs_interface.EnsembleInterface;
import data.MeteoData;
import listener.MeteoListener;
import objet_interface.BD_Ensemble;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws MqttException, InterruptedException, RemoteException, MalformedURLException {

        EnsembleInterface bd = new BD_Ensemble();

        java.rmi.registry.LocateRegistry.createRegistry(12346);
        Naming.rebind("rmi://localhost:12346/mon_serveur_capteurs", bd);

        while (true) {
            System.out.println(bd.getDatas());
            Thread.sleep(1000);
        }
    }
}
