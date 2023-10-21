package digital.byrd.fightclub.commands;

import digital.byrd.fightclub.generator.InitialGenerator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class Murder implements CommandExecutor {
    private InitialGenerator inventoryGenerator = new InitialGenerator();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        List<ItemStack> newItems = inventoryGenerator.makeInitialInventory();
        ItemStack[] asArray = new ItemStack[newItems.size()];
        newItems.toArray(asArray);
        inventory.setContents(asArray);
        return true;
    }
}
