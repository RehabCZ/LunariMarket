package cz.lunari.lunarimarket.managers;

import com.google.common.collect.Maps;
import cz.lunari.lunarimarket.objects.InventoryOwner;
import org.bukkit.entity.Player;

import java.util.Map;

public class InventoryManager {

    private final Map<Player, InventoryOwner> inventories = Maps.newHashMap();

    public InventoryOwner getInventoryOwner(Player player) {
        InventoryOwner inventoryOwner = inventories.get(player);

        if (inventoryOwner != null) {
            return inventoryOwner;
        }

        inventoryOwner = new InventoryOwner(player);
        inventories.put(player, inventoryOwner);

        return inventoryOwner;
    }
}
