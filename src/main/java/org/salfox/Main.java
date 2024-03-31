package org.salfox;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.salfox.Managers.PlayerTimeManager;
import org.salfox.commands.TimeCommand;
import org.salfox.listeners.TimeListener;

public class Main extends JavaPlugin {

    private PlayerTimeManager playerTimeManager;

    @Override
    public void onEnable() {
        // Carica il file di configurazione
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        // Inizializza la gestione del tempo del giocatore
        playerTimeManager = new PlayerTimeManager(this);

        getServer().getPluginManager().registerEvents(new TimeListener(playerTimeManager), this);

        getCommand("windyTempo").setExecutor(new TimeCommand(playerTimeManager, config));
    }
}

