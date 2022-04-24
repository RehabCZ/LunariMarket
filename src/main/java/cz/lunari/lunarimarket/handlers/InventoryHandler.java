package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.utils.InventoryHolderUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class InventoryHandler {

    private static final HashMap<Player, InventoryHolderUtils> inventoryHashMap = new HashMap<>();

    public InventoryHolderUtils getInventoryHolder(Player player){
        InventoryHolderUtils inventoryHolderUtils;
        if(inventoryHashMap.containsKey(player)){
            return inventoryHashMap.get(player);
        }
        else {
            inventoryHolderUtils = new InventoryHolderUtils(player);
            inventoryHashMap.put(player, inventoryHolderUtils);
            return inventoryHolderUtils;
        }
    }

}
