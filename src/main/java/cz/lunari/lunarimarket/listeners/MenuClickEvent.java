package cz.lunari.lunarimarket.listeners;

import cz.lunari.lunarimarket.inventory.InventoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuClickEvent implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getInventory().getHolder();
        if(holder instanceof InventoryMenu){
            e.setCancelled(true);
            if(e.getCurrentItem() == null) return;
            if(e.isShiftClick()) return;
            InventoryMenu menu = (InventoryMenu) holder;
            menu.handleMenu(e);
        }
    }
}
