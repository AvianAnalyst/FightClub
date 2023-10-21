package digital.byrd.fightclub;

import digital.byrd.fightclub.commands.Murder;
import org.bukkit.plugin.java.JavaPlugin;

public final class FightClub extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("finally");
        this.getCommand("murder").setExecutor(new Murder());
        getServer().getPluginManager().registerEvents(new Listener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("goodbye");
    }
}
