package digital.byrd.fightclub.generator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InitialGenerator {
    private final Random rand = new Random();

    public List<ItemStack> makeInitialInventory() {
        List<ItemStack> armor = this.makeArmor();
        List<ItemStack> weapons = this.makeWeapons();
        List<ItemStack> tools = this.makeTools();
        List<ItemStack> resources = Arrays.asList(
                new ItemStack(Material.ARROW, 64),
                new ItemStack(Material.GOLDEN_APPLE, rand.nextInt(2) + 1),
                new ItemStack(Material.GOLDEN_CARROT, 64),
                new ItemStack(Material.COBBLESTONE, 64)
        );
        return Stream.of(armor, weapons, resources).flatMap(Collection::stream).collect(Collectors.toList());

    }

    private List<ItemStack> makeTools() {
        ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack shovel = new ItemStack(Material.DIAMOND_SPADE);
        List<ItemStack> tools = new ArrayList<>(Arrays.asList(pick, axe, shovel));
        tools.stream().forEach(tool -> {
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

    private List<ItemStack> makeArmor() {
        List<ItemStack> armor = Arrays.asList(
                chooseOne(Material.DIAMOND_HELMET, Material.IRON_HELMET),
                chooseOne(Material.DIAMOND_LEGGINGS, Material.IRON_LEGGINGS),
                chooseOne(Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE),
                chooseOne(Material.DIAMOND_BOOTS, Material.IRON_BOOTS)
                );
        armor.forEach(item -> {
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
}
