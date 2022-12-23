package fr.kami.cllientcapteur.main;

import capteurs_interface.EnsembleInterface;
import fr.kami.cllientcapteur.main.listeners.CommandListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Main {

    public static DiscordApi api;
    public static EnsembleInterface bd;

    public static void main(String[] args) throws RemoteException, InterruptedException {
        System.setProperty("user.timezone", "Europe/Paris");

        try {
            bd = (EnsembleInterface) Naming.lookup("rmi://localhost:12346/mon_serveur_capteurs");


        } catch (Exception e) {
            e.printStackTrace();
        }

        String token = args[0];
        DiscordApi api = new DiscordApiBuilder().setToken(token)
                .setAllIntents().login().join();
        Main.api = api;

        api.updateActivity(ActivityType.LISTENING, "/help");

        api.bulkOverwriteGlobalApplicationCommands(Commands.all()).join();

        api.addSlashCommandCreateListener(new CommandListener());

        System.out.println("Je suis prêt !");
        while (true) {
            System.out.println(bd.getDatas());
            Thread.sleep(1000);
        }
    }
}
