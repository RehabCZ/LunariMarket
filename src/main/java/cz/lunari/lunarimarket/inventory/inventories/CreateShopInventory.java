package cz.lunari.lunarimarket.inventory.inventories;

import cz.lunari.lunarimarket.inventory.InventoryMenu;
import cz.lunari.lunarimarket.inventory.InventoryOwner;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import cz.lunari.lunarimarket.utils.ItemStackUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CreateShopInventory extends InventoryMenu {

    private final ItemStack increaseCount = ItemStackUtils.createSimpleItem(Material.GREEN_DYE, ChatMessageUtils.translateColors("&2&l+"), null);
    private final ItemStack decreaseCount = ItemStackUtils.createSimpleItem(Material.RED_DYE, ChatMessageUtils.translateColors("&4&l-"), null);
    private final ItemStack closeMenu = ItemStackUtils.createSimpleItem(Material.BARRIER, ChatMessageUtils.translateColors(localization.getString("inventory", "menu_exit")), null);
    private final ItemStack submitShop = ItemStackUtils.createSimpleItem(Material.STRUCTURE_VOID, ChatMessageUtils.translateColors("&2&lSubmit"), null);
    private final ItemStack filler = ItemStackUtils.createSimpleItem(Material.CYAN_STAINED_GLASS_PANE, "\n", null);

    public CreateShopInventory(InventoryOwner inventoryOwner) {
        super(inventoryOwner);
    }

    @Override
    public String getInventoryName() {
        return ChatMessageUtils.translateColors("&6&lCreate shop");
    }

    @Override
    public Integer getInventorySlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        ItemStack currentItem = e.getCurrentItem();

        ItemStack cursorItem = e.getCursor();

        Player player = (Player) e.getWhoClicked();

        if(cursorItem != null  && e.getSlot() == 20){
            inventory.setItem(20,cursorItem.clone());

            if (currentItem != null){
                player.getInventory().addItem(currentItem);
            }

            player.setItemOnCursor(cursorItem);
        }

        if(cursorItem != null && currentItem == null && e.getSlot() == 24){
            inventory.setItem(24,cursorItem.clone());

            if (currentItem != null){
                player.getInventory().addItem(currentItem);
            }

            player.setItemOnCursor(cursorItem);
        }

        e.setCancelled(true);
    }

    @Override
    public void setMenuItems() {

        inventory.setItem(19, increaseCount);
        inventory.setItem(23, increaseCount);

        inventory.setItem(21, decreaseCount);
        inventory.setItem(25, decreaseCount);

        inventory.setItem(38, closeMenu);
        inventory.setItem(42, submitShop);

        setFiller(filler);
    }
}
