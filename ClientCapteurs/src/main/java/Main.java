import capteurs_interface.EnsembleInterface;

import java.rmi.Naming;

public class Main {

    public static void main(String[] args) {

        try {
            EnsembleInterface bd = (EnsembleInterface) Naming.lookup("rmi://localhost:12346/mon_serveur_capteurs");

            int id = bd.newEnsemble();
            bd.addCapteurToEnsemble(id, "meteo", "meteo1");


            while (true) {
                System.out.println(bd.getDatas());
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
