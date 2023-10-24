package digital.byrd.fightclub.generator;

import digital.byrd.fightclub.commands.Murder;
import digital.byrd.fightclub.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InitialGenerator {
    private final Random rand = new Random();
    private Utils utils = new Utils();

    public List<ItemStack> makeInitialStorageInventory() {
        List<ItemStack> weapons = this.makeWeapons();
        List<ItemStack> tools = this.makeTools();
        List<ItemStack> resources = makeResources();
        return Stream.of(weapons, tools, resources)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    private List<ItemStack> makeResources() {
        ItemStack food =  new ItemStack(Material.GOLDEN_CARROT, 64);
        ItemStack ammo = new ItemStack(Material.ARROW, 64);
        ItemStack blocks =  new ItemStack(Material.COBBLESTONE, 64);
        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, rand.nextInt(2) + 1);
        return Arrays.asList(food, ammo, blocks, gapples);
    }

    private List<ItemStack> makeTools() {
        ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack shovel = new ItemStack(Material.DIAMOND_SPADE);
        List<ItemStack> tools = new ArrayList<>(Arrays.asList(pick, axe, shovel));
        tools.forEach(tool -> {
            tool.addEnchantment(Enchantment.DIG_SPEED, 1);
        });
        ItemStack waterBucket = new ItemStack(Material.WATER_BUCKET);
        tools.add(waterBucket);
        return tools;
    }

    private List<ItemStack> makeWeapons() {
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, rand.nextInt(3) + 1);

        ItemStack sword = chooseOne(Material.DIAMOND_SWORD, Material.IRON_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, rand.nextInt(3) + 1);

        return Arrays.asList(bow, sword);
    }

    public Map<String, ItemStack> makeArmor() {
        Map<String, ItemStack> armor = new HashMap<>();
        armor.put("helm", chooseOne(Material.DIAMOND_HELMET, Material.IRON_HELMET));
        armor.put("leggings", chooseOne(Material.DIAMOND_LEGGINGS, Material.IRON_LEGGINGS));
        armor.put("chestplate", chooseOne(Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE));
        armor.put("boots", chooseOne(Material.DIAMOND_BOOTS, Material.IRON_BOOTS));
        armor.values().forEach(item -> {
            if (rand.nextInt(2) == 0) {
                item.addEnchantment(Enchantment.PROTECTION_PROJECTILE, rand.nextInt(3) + 1);
            } else {
                item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, rand.nextInt(3) + 1);
            }
        });
        return armor;
    }

    private ItemStack chooseOne(Material opt1, Material opt2){
        if (rand.nextInt(2) == 0) {
            return  new ItemStack(opt1);
        }
        return new ItemStack(opt2);
    }

    public void setInventory(PlayerInventory inventory) {
        List<ItemStack> newItems = makeInitialStorageInventory();
        // set armor slots
        Map<String, ItemStack> armor = makeArmor();
        inventory.setHelmet(armor.get("helm"));
        inventory.setChestplate(armor.get("chestplate"));
        inventory.setLeggings(armor.get("leggings"));
        inventory.setBoots(armor.get("boots"));
        // collect the rest
        inventory.setContents(utils.itemStacksToArr(newItems));
    }
}
