package cz.lunari.lunarimarket.inventory;

import cz.lunari.lunarimarket.utils.InventoryHolderUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class InventoryMenu implements InventoryHolder {

    protected Inventory inventory;

    protected InventoryHolderUtils inventoryHolderUtils;

    public InventoryMenu(InventoryHolderUtils invUtils){
        this.inventoryHolderUtils = invUtils;
    }

    public abstract String getInventoryName();

    public abstract Integer getInventorySlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void openInventoryMenu(){
        inventory = Bukkit.createInventory(this, getInventorySlots(), getInventoryName());
        this.setMenuItems();
        inventoryHolderUtils.getOwner().openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
