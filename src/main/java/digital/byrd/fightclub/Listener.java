package digital.byrd.fightclub;

import digital.byrd.fightclub.generator.DropGenerator;
import digital.byrd.fightclub.generator.InitialGenerator;
import digital.byrd.fightclub.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;

public class Listener implements org.bukkit.event.Listener {
    private InitialGenerator inventoryGenerator = new InitialGenerator();
    private DropGenerator dropGenerator = new DropGenerator();
    private Utils utils = new Utils();

    private Logger logger = getLogger();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        logger.info("heard death");
        Player player = event.getEntity();
        PlayerInventory inventory = player.getInventory();
        List<ItemStack> desiredDrops = dropGenerator.chooseDrops(utils.itemStacksToList(inventory.getContents()));
        logger.warning(desiredDrops.toString());
        logger.warning("clearing desiredDrops");
        List<ItemStack> drops = event.getDrops();
        drops.clear();
        drops.addAll(desiredDrops);

        logger.warning("Cleared desiredDrops");
//        inventory.setContents(utils.itemStacksToArr(desiredDrops));
    }
}
