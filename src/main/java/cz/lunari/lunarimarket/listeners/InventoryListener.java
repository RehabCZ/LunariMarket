package cz.lunari.lunarimarket.listeners;

import cz.lunari.lunarimarket.inventory.InventoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        InventoryHolder holder = e.getInventory().getHolder();
        Inventory inventory = e.getClickedInventory();

        if (holder instanceof InventoryMenu menu) {

            if (inventory.getType() != InventoryType.PLAYER){
                e.setCancelled(true);
            }

            if (e.getCurrentItem() == null || e.isShiftClick()) {
                return;
            }

            menu.handleMenu(e);
        }
    }
}
