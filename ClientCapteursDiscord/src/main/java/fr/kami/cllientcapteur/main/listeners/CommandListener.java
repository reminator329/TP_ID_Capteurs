package fr.kami.cllientcapteur.main.listeners;

import fr.kami.cllientcapteur.commands.Command;
import fr.kami.cllientcapteur.main.Commands;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

public class CommandListener implements SlashCommandCreateListener {

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
        Command command = Commands.getByName(slashCommandInteraction.getCommandName());
        assert command != null;
        command.execute(event);
    }
}
