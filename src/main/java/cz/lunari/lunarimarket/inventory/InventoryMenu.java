package cz.lunari.lunarimarket.inventory;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.config.Configuration;
import cz.lunari.lunarimarket.config.Localization;
import cz.lunari.lunarimarket.objects.InventoryOwner;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class InventoryMenu implements InventoryHolder {

    protected Inventory inventory;

    protected InventoryOwner inventoryOwner;

    protected Configuration configuration = LunariMarket.getInstance().getConfiguration();

    protected Localization localization = LunariMarket.getInstance().getLocalization();

    public InventoryMenu(InventoryOwner inventoryOwner){
        this.inventoryOwner = inventoryOwner;
    }

    public abstract String getInventoryName();

    public abstract Integer getInventorySlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void openInventoryMenu(){
        inventory = Bukkit.createInventory(this, getInventorySlots(), getInventoryName());

        setMenuItems();

        inventoryOwner.getOwner().openInventory(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
