package fr.kami.cllientcapteur.commands.ensemble;

import fr.kami.cllientcapteur.commands.Command;
import fr.kami.cllientcapteur.main.Main;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteractionOption;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class CommandEnsemble extends Command {

    public CommandEnsemble() {
        super();
    }

    @Override
    public String getLabel() {
        return "ensemble";
    }

    @Override
    public String getDescription() {
        return "Gestion des ensembles de capteurs";
    }

    @Override
    public List<SlashCommandOption> getOptions() {
        int size;
        try {
            size = Main.bd.getDatas().size();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
        return List.of(
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "create", "Permet de créer un nouvel ensemble"),
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "visualiser", "Permet d'afficher toutes les données de tous les ensembles'"),
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "capteur", "Gestion des capteurs dans un ensemble", List.of(
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "add", "Permet d'ajouter un capteur à un ensemble'", List.of(
                                SlashCommandOption.createLongOption("id", "Identifiant de l'ensemble à modifier", true, 0, size+1),
                                SlashCommandOption.createStringOption("name", "Nom du capteur à ajouter", true)
                        )),
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "remove", "Permet de supprimer un capteur d'un ensemble", List.of(

                        ))
                ))
        );
    }

    @Override
    public boolean isEnabledInDms() {
        return true;
    }

    @Override
    public void otherCalls() {
        // setDefaultEnabledForPermissions(PermissionType.ADMINISTRATOR, PermissionType.BAN_MEMBERS);
    }

    @Override
    public void execute(SlashCommandCreateEvent event) {
        TextChannel textChannel = event.getInteraction().getChannel().orElse(null);
        if (textChannel == null) return;

        List<SlashCommandInteractionOption> options = event.getSlashCommandInteraction().getOptions();
        SlashCommandInteractionOption option = options.get(0);

        switch (option.getName()) {
            case "create" -> create(event);
            case "capteur" -> {
                SlashCommandInteractionOption option_capteur = option.getOptions().get(0);
                switch (option_capteur.getName()) {
                    case "add" -> {
                        Long id = option_capteur.getOptionByName("id").get().getLongValue().get();
                        String name = option_capteur.getOptionByName("name").get().getStringValue().get();
                        add(event, id, name);
                    }
                }
            }
            case "visualiser" -> visualiser(event);
        }

        SlashCommandInteractionOption create = event.getSlashCommandInteraction().getOptionByName("create").orElse(null);
        if (create == null) return;


    }

    private void visualiser(SlashCommandCreateEvent event) {
        try {
            Map<Integer, Map<String, Map<String, String>>> datas = Main.bd.getDatas();
            event.getInteraction().createImmediateResponder().setContent(datas.toString()).respond();
        } catch (RemoteException e) {
            event.getInteraction().createImmediateResponder().setContent("Erreur.").respond();
            e.printStackTrace();
        }
    }

    private void add(SlashCommandCreateEvent event, Long id, String name) {
        try {
            Main.bd.addCapteurToEnsemble(Math.toIntExact(id), name, name);
            event.getInteraction().createImmediateResponder().setContent("Capteur ajouté !").respond();
        } catch (RemoteException e) {
            event.getInteraction().createImmediateResponder().setContent("Erreur.").respond();
            e.printStackTrace();
        }
    }

    private void create(SlashCommandCreateEvent event) {
        try {
            int id = Main.bd.newEnsemble();
            event.getInteraction().createImmediateResponder().setContent("Ensemble numéro " + id + " créé !").respond();
        } catch (RemoteException e) {
            event.getInteraction().createImmediateResponder().setContent("Erreur.").respond();
            e.printStackTrace();
        }
    }
}
