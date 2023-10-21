package digital.byrd.fightclub;

import digital.byrd.fightclub.generator.DropGenerator;
import digital.byrd.fightclub.generator.InitialGenerator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {
    private InitialGenerator inventoryGenerator = new InitialGenerator();
    private DropGenerator dropGenerator = new DropGenerator();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }
}
