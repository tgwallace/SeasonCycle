package me.hardbuckaroo.seasoncycle;

import me.hardbuckaroo.seasoncycle.SQL.Database;
import me.hardbuckaroo.seasoncycle.SQL.SQLite;
import me.hardbuckaroo.seasoncycle.commands.GetSeason;
import me.hardbuckaroo.seasoncycle.commands.SeasonAdvance;
import me.hardbuckaroo.seasoncycle.commands.SetSeason;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SeasonCycle extends JavaPlugin {

    private Database db;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.db = new SQLite(this);
        this.db.load();

        Bukkit.getPluginManager().registerEvents(new ChangeAndSaveBiome(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinMessage(this), this);
        MeltIceSnow melt = new MeltIceSnow(this);
        Bukkit.getPluginManager().registerEvents(melt, this);
        SnowOnPlants snow = new SnowOnPlants(this);
        Bukkit.getPluginManager().registerEvents(snow, this);
        Bukkit.getPluginManager().registerEvents(new BonemealEnhancer(this), this);

        SeasonAdvance seasonAdvance = new SeasonAdvance(this);
        getCommand("seasonadvance").setExecutor(seasonAdvance);

        GetSeason getSeason = new GetSeason(this);
        getCommand("getseason").setExecutor(getSeason);

        SetSeason setSeason = new SetSeason(this);
        getCommand("setseason").setExecutor(setSeason);

        PrintSeason printSeason = new PrintSeason(this);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.now();
        LocalDate lastChanged;

        if(this.getConfig().getString("LastChanged").equals("05-17-2009")) {
            this.getConfig().set("LastChanged",date.format(formatter));
            this.saveConfig();
            this.reloadConfig();
        }
        lastChanged = LocalDate.parse(this.getConfig().getString("LastChanged"), formatter);

        if(ChronoUnit.DAYS.between(lastChanged,date) >= this.getConfig().getInt("SeasonLength") && this.getConfig().getInt("SeasonLength")!=0){
            seasonAdvance.advanceSeason(1);
        }
    }

    public Database getRDatabase() {
        return this.db;
    }

    @Override
    public void onDisable() {
    }
}
