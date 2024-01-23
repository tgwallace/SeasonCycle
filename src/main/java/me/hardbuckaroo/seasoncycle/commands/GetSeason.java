package me.hardbuckaroo.seasoncycle.commands;

import me.hardbuckaroo.seasoncycle.PrintSeason;
import me.hardbuckaroo.seasoncycle.SeasonCycle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetSeason implements CommandExecutor {

    private final SeasonCycle plugin;
    public GetSeason(SeasonCycle plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PrintSeason printSeason = new PrintSeason(plugin);
        Player player = (Player) sender;

        player.sendRawMessage(printSeason.printSeason());
        return true;
    }
}
