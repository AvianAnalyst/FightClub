package digital.byrd.fightclub.commands;

import static org.bukkit.Bukkit.getLogger;
import digital.byrd.fightclub.FightClub;
import digital.byrd.fightclub.generator.InitialGenerator;
import digital.byrd.fightclub.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Murder implements CommandExecutor {
    private FightClub thisPlugin = FightClub.getPlugin(FightClub.class);
    private InitialGenerator inventoryGenerator = new InitialGenerator();
    private Utils utils = new Utils();
    private Logger logger = getLogger();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (thisPlugin.getKittedPlayers().contains(player.getUniqueId())) {
            player.sendMessage("You got your stuff already. Go kill.");
            return true;
        }
        PlayerInventory inventory = player.getInventory();
        List<ItemStack> newItems = inventoryGenerator.makeInitialStorageInventory();
        logger.info("New items below");
        logger.info(newItems.toString());
        // set armor slots
        Map<String, ItemStack> armor = inventoryGenerator.makeArmor();
        inventory.setHelmet(armor.get("helm"));
        inventory.setChestplate(armor.get("chestplate"));
        inventory.setLeggings(armor.get("leggings"));
        inventory.setBoots(armor.get("boots"));
        // collect the rest


        inventory.setContents(utils.itemStacksToArr(newItems));
        player.setHealth(20);
        player.setFoodLevel(20);
        thisPlugin.addKittedPlayer(player.getUniqueId());
        return true;
    }
}
