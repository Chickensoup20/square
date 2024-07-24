package net.chicken.projects.square.events;

import net.chicken.projects.square.plotmanagement.Plot;
import net.chicken.projects.square.plotmanagement.PlotTypes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InternalEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Plot plot = new Plot(event.getPlayer().getUniqueId(), PlotTypes.LARGE);
    }
}
