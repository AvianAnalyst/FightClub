package digital.byrd.fightclub;

import digital.byrd.fightclub.commands.Murder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class FightClub extends JavaPlugin {
    private Set<UUID> kittedPlayers = new HashSet<>();

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

    public Set<UUID> getKittedPlayers() {
        return kittedPlayers;
    }

    public void addKittedPlayer(UUID playerUUID) {
        this.kittedPlayers.add(playerUUID);
    }
    public void removeKittedPlayer(UUID playerUUID) {
        this.kittedPlayers.remove(playerUUID);
    }
}
