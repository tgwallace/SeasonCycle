package me.hardbuckaroo.seasoncycle.commands;

import me.hardbuckaroo.seasoncycle.PrintSeason;
import me.hardbuckaroo.seasoncycle.SeasonCycle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

public class SeasonAdvance implements CommandExecutor {

    private final SeasonCycle plugin;
    public SeasonAdvance(SeasonCycle plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            return false;
        }
        int seasonsAdvanced = parseInt(args[0]);
        Player player = (Player) sender;
        if(seasonsAdvanced >3 | seasonsAdvanced <1 | args.length!=1)
        {
            player.sendRawMessage("Seasons Advanced must be 1, 2, or 3");
            return false;
        }
        advanceSeason(seasonsAdvanced);

        PrintSeason printSeason = new PrintSeason(plugin);
        sender.sendMessage(printSeason.printSeason());

        return true;
    }

    public void advanceSeason(int seasonsAdvanced){
        int season = plugin.getConfig().getInt("Season");
        int year = plugin.getConfig().getInt("Year");
        season=season+seasonsAdvanced;
        if (season>=4)
        {
            year++;
            season = season-4;
        }

        plugin.getConfig().set("Season",season);
        plugin.getConfig().set("Year",year);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        plugin.getConfig().set("LastChanged",date.format(formatter));

        plugin.saveConfig();
    }
}
