package io.github.slazurin.sldeathlocationbroadcaster.Commands;

import io.github.slazurin.sldeathlocationbroadcaster.SLDeathLocationBroadcaster;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;


public class DeathLocation implements TabExecutor {
    private final SLDeathLocationBroadcaster plugin;
    
    public DeathLocation(SLDeathLocationBroadcaster plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmnd, String string, String[] args) {
        if (args.length == 1) {
            List playerNames = new ArrayList<String>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().toUpperCase().startsWith(args[0].toUpperCase())) {
                    playerNames.add(p.getName());
                }
            }
            return playerNames;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String label, String[] args) {
        if (cs == null) return false;
        if (!(cs instanceof Player) && args.length != 1) {
            cs.sendMessage("Only players may use this command without argument!");
            return true;
        }
        if (args.length > 1) {
            cs.sendMessage("Expected 0 or 1 argument");
            return true;
        }
        Player p;
        boolean hasPerm;
        if (args.length == 1) {
            if (!(cs instanceof Player)) {
                hasPerm = true;
            } else {
                hasPerm = cs.hasPermission("sldlb.deathlocation.otherplayer");
            }
            p = Bukkit.getPlayer(args[0]);
        } else {
            hasPerm = cs.hasPermission("sldlb.deathlocation");
            p = (Player) cs;
        }
        
        if (!hasPerm) {
            cs.sendMessage("You do not have permission to use this command.");
            return true;
        }
        
        
        
        if (p != null) {
            cs.sendMessage(ChatColor.RED + this.plugin.getApi().getDeathLocationText(p));
        } else {
            cs.sendMessage("Player not found.");
        }
        return true;
        
    }
    
}
