package me.hardbuckaroo.seasoncycle.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import me.hardbuckaroo.seasoncycle.SeasonCycle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.hardbuckaroo.seasoncycle.SQL.Error; // YOU MUST IMPORT THE CLASS ERROR, AND ERRORS!!!
import me.hardbuckaroo.seasoncycle.SQL.Errors;
import me.hardbuckaroo.seasoncycle.SeasonCycle; // Import main class!


public abstract class Database {
    SeasonCycle plugin;
    Connection connection;

    // The name of the table we created back in SQLite class.
    public String table = "biomeDB";
    public Database(SeasonCycle instance){
        plugin = instance;
    }

    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize(){
        connection = getSQLConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE key = ?");
            ResultSet rs = ps.executeQuery();
            close(ps,rs);
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retrieve connection", ex);
        }
    }

    // These are the methods you can use to get things out of your database. You of course can make new ones to return different things in the database.
    // This returns the number of people the player killed.
    public String getBiome(String key) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = getSQLConnection();
                ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE key = '" + key + "';");

                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("key").equalsIgnoreCase(key.toLowerCase())) {
                        return rs.getString("originalBiome");
                    }
                }
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
            } finally {
                try {
                    if (ps != null)
                        ps.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException ex) {
                    plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
                }
            }
        return "";
    }

    // Now we need methods to save things to the database
    public void setBiome(String key, String biome) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO " + table + " (key,originalBiome) VALUES(?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            ps.setString(1, key);                                             // YOU MUST put these into this line!! And depending on how many
            ps.setString(2, biome);
            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }

    public void close(PreparedStatement ps,ResultSet rs){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}
