/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.slazurin.sldeathlocationbroadcaster.Commands;

import io.github.slazurin.sldeathlocationbroadcaster.SLDeathLocationBroadcaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

/**
 *
 * @author Azuri
 */
public class DebugInfo implements TabExecutor {
    private final SLDeathLocationBroadcaster plugin;
    
    public DebugInfo(SLDeathLocationBroadcaster plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmnd, String string, String[] strings) {
        return new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (cs == null) return true;
        if (cs instanceof Player) {
            if (!cs.hasPermission("sldlb.debuginfo")) {
                cs.sendMessage("You do not have permission to use this command.");
                return true;
            }
        }
        
        Map<UUID, Location> dl = this.plugin.getApi().getDeathLocations();
        cs.sendMessage(ChatColor.GREEN + "SLDeathLocationBroadcaster Version: " + this.plugin.getDescription().getVersion());
        cs.sendMessage(ChatColor.GREEN + "Player Death Locations:");
        for (Map.Entry<UUID, Location> e : dl.entrySet()) {
            Player p = Bukkit.getPlayer(e.getKey());
            if (p != null)
                cs.sendMessage(ChatColor.GREEN + p.getName() + ": x=" + 
                        e.getValue().getBlockX() + " y=" +
                        e.getValue().getBlockY() + " z=" +
                        e.getValue().getBlockZ());
        }
        return true;
    }
    
}
