package cz.lunari.lunarimarket.inventory.inventories;

import cz.lunari.lunarimarket.inventory.InventoryMenu;
import cz.lunari.lunarimarket.inventory.InventoryOwner;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import cz.lunari.lunarimarket.utils.ItemStackUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class MainMenuInventory extends InventoryMenu {

    private final ItemStack createShop = ItemStackUtils.createSimpleItem(Material.BARREL, ChatMessageUtils.translateColors(localization.getString("inventory.menu_create_shop")), null);
    private final ItemStack createVault = ItemStackUtils.createSimpleItem(Material.NAME_TAG, ChatMessageUtils.translateColors(localization.getString("inventory.menu_create_vault")), null);
    private final ItemStack viewShops = ItemStackUtils.createPlayerSkull(inventoryOwner.getOwner(), ChatMessageUtils.translateColors(localization.getString("inventory.menu_list_shops")), Collections.singletonList(ChatMessageUtils.translateColors("&7") + inventoryOwner.getOwner().getName()));
    private final ItemStack closeMenu = ItemStackUtils.createSimpleItem(Material.BARRIER, ChatMessageUtils.translateColors(localization.getString("inventory.menu_exit")), null);
    private final ItemStack filler = ItemStackUtils.createSimpleItem(Material.CYAN_STAINED_GLASS_PANE, "\n", null);

    CreateShopInventory shopInventory;

    public MainMenuInventory(InventoryOwner owner) {
        super(owner);

        shopInventory = new CreateShopInventory(owner);
    }

    @Override
    public String getInventoryName() {
        return ChatMessageUtils.translateColors(localization.getString("inventory.menu_title"));
    }

    @Override
    public Integer getInventorySlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();

        if (item == null) {
            return;
        }

        if (item.equals(createShop)) {
            e.getWhoClicked().closeInventory();
            shopInventory.openInventoryMenu();
        } else if (item.equals(createVault)) {
            e.getWhoClicked().closeInventory();
            e.getWhoClicked().sendMessage(ChatMessageUtils.translateColors("&7You clicked vault Creation"));
        } else if (item.equals(viewShops)) {
            e.getWhoClicked().closeInventory();
            e.getWhoClicked().sendMessage(ChatMessageUtils.translateColors("&7You clicked view your shops"));
        } else if (item.equals(closeMenu)) {
            e.getWhoClicked().closeInventory();
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(10, createShop);
        inventory.setItem(13, createVault);
        inventory.setItem(16, viewShops);
        inventory.setItem(31, closeMenu);

        setFiller(filler);
    }
}
