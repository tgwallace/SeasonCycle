package me.hardbuckaroo.seasoncycle;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class JoinMessage implements Listener {

    private final SeasonCycle plugin;
    public JoinMessage(SeasonCycle plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void  onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        int season = plugin.getConfig().getInt("Season");
        int year = plugin.getConfig().getInt("Year");
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate lastChanged = LocalDate.parse(plugin.getConfig().getString("LastChanged"), formatter);
        int daysLeft = (int) (plugin.getConfig().getInt("SeasonLength") - ChronoUnit.DAYS.between(lastChanged,date));
        String remain="";
        String seasonText="";

        if(season == 0) {
            seasonText = "&a&lIt is the Spring of year " + year + "!";
            if (plugin.getConfig().getInt("SeasonLength")!=0) {
                if(daysLeft != 1) remain = daysLeft + " days remain until Summer.";
                else remain = daysLeft + " day remains until Summer.";
            }
        } else if(season == 1) {
            seasonText = "&c&lIt is the Summer of year " + year + "!";
            if (plugin.getConfig().getInt("SeasonLength")!=0) {
                if(daysLeft != 1) remain = daysLeft + " days remain until Fall.";
                else remain = daysLeft + " day remains until Fall.";
            }
        } else if(season == 2) {
            seasonText = "&6&lIt is the Fall of year " + year + "!";
            if (plugin.getConfig().getInt("SeasonLength")!=0) {
                if(daysLeft != 1) remain = daysLeft + " days remain until Winter.";
                else remain = daysLeft + " day remains until Winter.";
            }
        } else if(season == 3) {
            seasonText = "&b&lIt is the Winter of year " + year + "!";
            if (plugin.getConfig().getInt("SeasonLength")!=0) {
                if(daysLeft != 1) remain = daysLeft + " days remain until Spring.";
                else remain = daysLeft + " day remains until Spring.";
            }
        }

        TextComponent seasoncomponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', seasonText));
        Bukkit.getScheduler().runTaskLater(plugin, () -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR,seasoncomponent), 200L);

        TextComponent leftcomponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&o"+remain));
        Bukkit.getScheduler().runTaskLater(plugin, () -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR,leftcomponent), 260L);
    }
}
