package org.salfox.listeners;

import org.bukkit.event.Listener;
import org.salfox.Managers.PlayerTimeManager;

public class TimeListener implements Listener {

    private PlayerTimeManager playerTimeManager;

    public TimeListener(PlayerTimeManager playerTimeManager) {
        this.playerTimeManager = playerTimeManager;
    }

}


