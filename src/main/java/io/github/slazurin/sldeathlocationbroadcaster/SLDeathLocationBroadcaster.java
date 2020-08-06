package io.github.slazurin.sldeathlocationbroadcaster;

import io.github.slazurin.sldeathlocationbroadcaster.Commands.DeathLocation;
import io.github.slazurin.sldeathlocationbroadcaster.Commands.DebugInfo;
import io.github.slazurin.sldeathlocationbroadcaster.Listeners.DeathListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SLDeathLocationBroadcaster extends JavaPlugin {
    private SLDeathLocationBroadcasterApi api;
    
    @Override
    public void onEnable() {
        this.api = new SLDeathLocationBroadcasterApi(this);
        registerCommands();
        registerListeners();
        this.getLogger().info("SLDeathLocationBradcaster enabled");
    }

    public SLDeathLocationBroadcasterApi getApi() {
        return this.api;
    }
    
    private void registerCommands() {
        this.getCommand("death").setExecutor(new DeathLocation(this));
        this.getCommand("debuginfo").setExecutor(new DebugInfo(this));
    }
    
    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new DeathListener(this), this);
    }
}
