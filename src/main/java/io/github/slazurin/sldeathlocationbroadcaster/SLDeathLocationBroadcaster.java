package io.github.slazurin.sldeathlocationbroadcaster;

import io.github.slazurin.sldeathlocationbroadcaster.Commands.DeathLocation;
import io.github.slazurin.sldeathlocationbroadcaster.Commands.DebugInfo;
import io.github.slazurin.sldeathlocationbroadcaster.Listeners.DeathListener;
import io.github.slazurin.slvanishapi.SLVanishApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SLDeathLocationBroadcaster extends JavaPlugin {
    private SLDeathLocationBroadcasterApi api;
    private SLVanishApi svapi;
    
    @Override
    public void onEnable() {
        this.api = new SLDeathLocationBroadcasterApi(this);
        this.svapi = (SLVanishApi) this.getServer().getPluginManager().getPlugin("SLVanishApi");
        registerCommands();
        registerListeners();
        this.getLogger().info("SLDeathLocationBradcaster enabled");
    }

    public SLDeathLocationBroadcasterApi getApi() {
        return this.api;
    }

    public SLVanishApi getSvapi() {
        return svapi;
    }
    
    public boolean isSvapiEnabled() {
        return svapi != null;
    }
    
    private void registerCommands() {
        this.getCommand("death").setExecutor(new DeathLocation(this));
        this.getCommand("debuginfo").setExecutor(new DebugInfo(this));
    }
    
    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new DeathListener(this), this);
    }
}
