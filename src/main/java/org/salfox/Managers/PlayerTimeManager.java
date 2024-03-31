package org.salfox.Managers;

import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;


public class PlayerTimeManager {

    private JavaPlugin plugin;
    private FileConfiguration dataConfig;

    public PlayerTimeManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        // Se il file di configurazione non esiste, carica la versione predefinita
        if (!configFile.exists()) {
            plugin.saveDefaultConfig();
        }

        // Carica il file di configurazione
        dataConfig = plugin.getConfig();

        // Se il file di configurazione è vuoto, imposta i valori predefiniti manualmente
        if (dataConfig.getKeys(true).isEmpty()) {
            dataConfig.set("messaggio.intestazione", "&3-----------------------------------------------");
            dataConfig.set("messaggio.informazioni_utente", "&e&lINFORMAZIONI UTENTE &b");
            dataConfig.set("messaggio.nome_utente", "&e");
            dataConfig.set("messaggio.tempo_giocato", "&6> &f&lTempo Giocato: &b");
            dataConfig.set("messaggio.piede_pagina", "&3------------------------------------------------");

            // Salva i valori predefiniti nel file di configurazione
            plugin.saveConfig();
        }
    }

    public FileConfiguration getConfig() {
        return dataConfig;
    }
    public long getPlayerTime(Player player) {
        return (long) player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
    }

    public String formatTime(long seconds) {
        long minutes = seconds / 60;
        long hours = minutes / 60;

        // Calcola il numero di minuti e secondi senza lo zero iniziale se il numero è inferiore a 10
        long remainingMinutes = minutes % 60;
        long remainingSeconds = seconds % 60;

        // Costruisci la stringa di formato in base ai valori calcolati
        String formatString = getString(hours, remainingMinutes, remainingSeconds);

        // Formatta la stringa con i valori calcolati
        return String.format(formatString, hours, remainingMinutes, remainingSeconds);
    }

    @NotNull
    private static String getString(long hours, long remainingMinutes, long remainingSeconds) {
        String formatString = "%d Ore. %d Minuti. %d Secondi.";

        // Se il numero di ore, minuti o secondi è inferiore a 10, usa il formato senza lo zero iniziale
        if (hours < 10) {
            formatString = "%d Ore. %02d Minuti. %02d Secondi.";
        }
        if (remainingMinutes < 10) {
            formatString = "%d Ore. %d Minuti. %02d Secondi.";
        }
        if (remainingSeconds < 10) {
            formatString = "%d Ore. %d Minuti. %d Secondi.";
        }
        return formatString;
    }

}

