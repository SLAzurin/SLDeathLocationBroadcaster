package io.github.slazurin.sldeathlocationbroadcaster;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SLDeathLocationBroadcasterApi {
    private final SLDeathLocationBroadcaster plugin;
    
    private final Map<UUID,Location> deathLocations;
    
    SLDeathLocationBroadcasterApi(SLDeathLocationBroadcaster plugin) {
        this.plugin = plugin;
        this.deathLocations = new HashMap<>();
    }

    public Map<UUID, Location> getDeathLocations() {
        return deathLocations;
    }

    public void registerDeathLocation(Player deadPlayer, Location deathLocation) {
        if (this.deathLocations.containsKey(deadPlayer.getUniqueId())) {
            this.deathLocations.replace(deadPlayer.getUniqueId(), deathLocation);
        } else {
            this.deathLocations.put(deadPlayer.getUniqueId(), deathLocation);
        }
    }
    
    public Location getDeathLocation(Player p) {
        if (p == null) {
            return null;
        }
        
        if (this.deathLocations.containsKey(p.getUniqueId())) {
            return this.deathLocations.get(p.getUniqueId());
        } else {
            return null;
        }
    }
    
    public String getDeathLocationText(Player p) {
        Location l = getDeathLocation(p);
        if (l == null) {
            return p.getName() + " did not die recently.";
        }
        
        String worldName = "World";
        if (l.getWorld().getName().endsWith("_the_end")) {
            worldName = "End";
        }
        if (l.getWorld().getName().endsWith("_nether")) {
            worldName = "Nether";
        }
        
        return p.getName() + " died at x: " 
                        + l.getBlockX() + " y: " + 
                        l.getBlockY() + " z: " + 
                        l.getBlockZ() + " (" + worldName + ")";
    }
    
}
