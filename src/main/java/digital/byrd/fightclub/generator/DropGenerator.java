package digital.byrd.fightclub.generator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import static org.bukkit.Bukkit.getLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DropGenerator {
    private final Random rand = new Random();
    private final Logger logger = getLogger();

    public List<ItemStack> chooseDrops(List<ItemStack> inventory) {
        Map<Integer, List<ItemStack>> sortedItems = sortItems(inventory);
        logger.info(String.valueOf(sortedItems.size()));

        Integer highestTier = sortedItems.keySet().stream().mapToInt(v -> v).max().orElse(0);
        List<ItemStack> highestTierItems = sortedItems.get(highestTier);
        ItemStack gear1 = null;
        if (highestTierItems.size() > 0) {
            gear1 = highestTierItems.get(rand.nextInt(highestTierItems.size()));
        }
        sortedItems.get(highestTier).remove(gear1);

        List<ItemStack> allItems = sortedItems.keySet().stream()
                .map(sortedItems::get)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        ItemStack gear2 = null;
        if (allItems.size() > 0) {
            gear2 = allItems.get(rand.nextInt(allItems.size()));
        }
        return Arrays.asList(
                new ItemStack(Material.ARROW, 32),
                new ItemStack(Material.GOLDEN_APPLE, rand.nextInt(2) + 1),
                gear1,
                gear2
        );
    }

    private Map<Integer, List<ItemStack>> sortItems(List<ItemStack> inventory) {

        Map<Integer, List<ItemStack>> sortedItems = new HashMap<>();
        inventory.forEach(itemStack -> {
            Integer tier;
            if (itemStack == null) {
                return;
            }
            if (itemStack.getType().equals(Material.BOW)) {
                tier = determineBowTier(itemStack);
            } else if (isArmor(itemStack) || isSword(itemStack)) {
                tier = determineMeleeTier(itemStack);
            } else {
                return;
            }
            if (sortedItems.containsKey(tier)){
                List<ItemStack> list = sortedItems.get(tier);
                list.add(itemStack);
            } else {
                sortedItems.put(tier, new ArrayList<>(Collections.singletonList(itemStack)));
            }
        });
        return sortedItems;
    }

    private Integer determineMeleeTier(ItemStack item) {
        int tier;
        if (isArmor(item)) {
            if (item.containsEnchantment(Enchantment.PROTECTION_PROJECTILE)) {
                tier = item.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE);
            } else {
                tier = item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
            }
        } else {
            tier = item.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
        }
        if (isDiamond(item)) {
            tier = tier + 1;
        }
        return tier;
    }

    private Integer determineBowTier(ItemStack bow) {
        switch (bow.getEnchantmentLevel(Enchantment.ARROW_DAMAGE)) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 4;
        }
    }

    private boolean isDiamond(final ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        return itemStack.getType().name().startsWith("DIAMOND_");
    }

    private boolean isSword(final ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        return itemStack.getType().name().endsWith("_SWORD");
    }

    private boolean isArmor(final ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        final String typeNameString = itemStack.getType().name();
        return typeNameString.endsWith("_HELMET")
                || typeNameString.endsWith("_CHESTPLATE")
                || typeNameString.endsWith("_LEGGINGS")
                || typeNameString.endsWith("_BOOTS");
    }


}
