package digital.byrd.fightclub.commands;

import static org.bukkit.Bukkit.getLogger;
import digital.byrd.fightclub.FightClub;
import digital.byrd.fightclub.generator.InitialGenerator;
import digital.byrd.fightclub.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Murder implements CommandExecutor {
    private FightClub thisPlugin = FightClub.getPlugin(FightClub.class);
    private InitialGenerator inventoryGenerator = new InitialGenerator();
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
        player.setHealth(20);
        player.setFoodLevel(20);
        PlayerInventory inventory = player.getInventory();
        inventoryGenerator.setInventory(inventory);
        thisPlugin.addKittedPlayer(player.getUniqueId());
        return true;
    }

}
