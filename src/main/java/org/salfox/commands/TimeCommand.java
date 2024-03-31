package org.salfox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.salfox.Managers.PlayerTimeManager;

public class TimeCommand implements CommandExecutor {

    private PlayerTimeManager playerTimeManager;
    private final FileConfiguration config;

    public TimeCommand(PlayerTimeManager playerTimeManager, FileConfiguration config) {
        this.playerTimeManager = playerTimeManager;
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("windyTempo")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Questo comando puo' essere eseguito solo da un giocatore.");
                return true;
            }
            Player player;
            if(args.length == 1){
                player = Bukkit.getPlayer(args[0]);

            } else {
                player = (Player) sender;
            }
            if(player == null) {  sender.sendMessage("Il Player selezionato non è valido."); return true; }
            long timeInMinutes = playerTimeManager.getPlayerTime(player);
            String formattedTime = playerTimeManager.formatTime(timeInMinutes);
            String playerName = player.getName();
            String header = config.getString("messaggio.intestazione");
            String piedeHeader = config.getString("messaggio.piede_pagina");
            String infoHeader = config.getString("messaggio.informazioni_utente");
            String timeInfo = config.getString("messaggio.tempo_giocato");

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', header));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', infoHeader) + playerName);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', timeInfo) + formattedTime);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', piedeHeader));
            return true;

        }
        return false;
    }
}




