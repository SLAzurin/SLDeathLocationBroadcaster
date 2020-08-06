package io.github.slazurin.sldeathlocationbroadcaster.Listeners;

import io.github.slazurin.sldeathlocationbroadcaster.SLDeathLocationBroadcaster;
import io.github.slazurin.sldeathlocationbroadcaster.SLDeathLocationBroadcasterApi;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    private SLDeathLocationBroadcaster plugin;
    
    public DeathListener(SLDeathLocationBroadcaster plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player deadPlayer = e.getEntity();
        Location deathLocation = e.getEntity().getLocation();
        SLDeathLocationBroadcasterApi api = this.plugin.getApi();
        api.registerDeathLocation(deadPlayer, deathLocation);
        
        String worldName = e.getEntity().getWorld().getName();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getName().equals(worldName)) {
                p.sendMessage(ChatColor.RED + api.getDeathLocationText(deadPlayer));
            }
        }
    }
    
}
