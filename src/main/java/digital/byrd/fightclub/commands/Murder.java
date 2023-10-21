package digital.byrd.fightclub.commands;

import digital.byrd.fightclub.generator.InitialGenerator;
import digital.byrd.fightclub.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class Murder implements CommandExecutor {
    private InitialGenerator inventoryGenerator = new InitialGenerator();
    private Utils utils = new Utils();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        List<ItemStack> newItems = inventoryGenerator.makeInitialInventory();
        inventory.setContents(utils.itemStacksToArr(newItems));
        return true;
    }
}
