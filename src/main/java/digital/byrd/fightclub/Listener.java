package digital.byrd.fightclub;

import digital.byrd.fightclub.generator.DropGenerator;
import digital.byrd.fightclub.generator.InitialGenerator;
import digital.byrd.fightclub.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;

public class Listener implements org.bukkit.event.Listener {
    private InitialGenerator inventoryGenerator = new InitialGenerator();
    private FightClub thisPlugin = FightClub.getPlugin(FightClub.class);
    private DropGenerator dropGenerator = new DropGenerator();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        thisPlugin.removeKittedPlayer(event.getEntity().getUniqueId());
        List<ItemStack> drops = event.getDrops();
        List<ItemStack> desiredDrops = dropGenerator.chooseDrops(drops);
        drops.clear();
        drops.addAll(desiredDrops);
        event.setDroppedExp(10);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        inventoryGenerator.setInventory(player.getInventory());
        thisPlugin.addKittedPlayer(player.getUniqueId());
    }
}
