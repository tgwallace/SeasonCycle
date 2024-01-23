package me.hardbuckaroo.seasoncycle.SQL;

import me.hardbuckaroo.seasoncycle.SeasonCycle;

import java.util.logging.Level;

public class Error {
    public static void execute(SeasonCycle plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }
    public static void close(SeasonCycle plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}