package digital.byrd.fightclub.utils;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public final ItemStack[] itemStacksToArr(List<ItemStack> items) {
        ItemStack[] asArray = new ItemStack[items.size()];
        return items.toArray(asArray);
    }
    public final List<ItemStack> itemStacksToList(ItemStack[] items) {
        return Arrays.stream(items).collect(Collectors.toList());
    }
}
