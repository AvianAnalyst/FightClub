package digital.byrd.fightclub;

import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FightClub extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("finally");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("goodbye");
    }
}
