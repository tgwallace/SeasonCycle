package me.hardbuckaroo.seasoncycle.commands;
import me.hardbuckaroo.seasoncycle.PrintSeason;
import me.hardbuckaroo.seasoncycle.SeasonCycle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SetSeason implements CommandExecutor {

    private final SeasonCycle plugin;
    public SetSeason(SeasonCycle plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        int newSeason = Integer.parseInt(args[0]);
        int newYear;

        if((args.length!=1 && args.length!=2) | newSeason>3 | newSeason<0){
            sender.sendMessage("Season must be between 0-3. 0 for Spring, 1 for Summer, 2 for Fall, 3 for Winter.");
            return false;
        }

        if(args.length==1) setSeason(newSeason);
        if(args.length==2) {
            newYear = Integer.parseInt(args[1]);
            setSeason(newSeason, newYear);
        }

        PrintSeason printSeason = new PrintSeason(plugin);
        sender.sendMessage(printSeason.printSeason());
        return true;
    }

    public void setSeason(int newSeason, int newYear){
        plugin.getConfig().set("Season",newSeason);
        plugin.getConfig().set("Year",newYear);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        plugin.getConfig().set("LastChanged",date.format(formatter));
        plugin.saveConfig();
    }

    public void setSeason(int newSeason){
        plugin.getConfig().set("Season",newSeason);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        plugin.getConfig().set("LastChanged",date.format(formatter));
        plugin.saveConfig();
    }
}
