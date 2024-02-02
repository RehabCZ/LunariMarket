package cz.lunari.lunarimarket.inventory.inventories;

import cz.lunari.lunarimarket.inventory.InventoryMenu;
import cz.lunari.lunarimarket.objects.InventoryOwner;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import cz.lunari.lunarimarket.utils.ItemStackUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class MainMenuInventory extends InventoryMenu {

    CreateShopInventory shopInventory;

    public MainMenuInventory(InventoryOwner owner) {
        super(owner);

        shopInventory = new CreateShopInventory(owner);
    }

    @Override
    public String getInventoryName() {
        return ChatMessageUtils.translateColors(localization.getString("inventory","menu_title"));
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

        switch (item.getType()) {
            case BARREL:
                e.getWhoClicked().closeInventory();
                shopInventory.openInventoryMenu();
                break;

            case NAME_TAG:
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatMessageUtils.translateColors("&7You clicked vault Creation"));
                break;

            case PLAYER_HEAD:
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatMessageUtils.translateColors("&7You clicked view your shops"));
                break;

            case BARRIER:
                e.getWhoClicked().closeInventory();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack createShop = ItemStackUtils.createSimpleItem(Material.BARREL, ChatMessageUtils.translateColors(localization.getString("inventory","menu_create_shop")), Collections.singletonList(ChatMessageUtils.translateColors("&eCool af.")));
        ItemStack createVault = ItemStackUtils.createSimpleItem(Material.NAME_TAG, ChatMessageUtils.translateColors(localization.getString("inventory","menu_create_vault")), Collections.singletonList(ChatMessageUtils.translateColors("&eCool af hehehe.")));
        ItemStack viewShops = ItemStackUtils.createPlayerSkull(inventoryOwner.getOwner(), ChatMessageUtils.translateColors(localization.getString("inventory","menu_list_shops")), Collections.singletonList(ChatMessageUtils.translateColors("&7Mein god")));
        ItemStack closeMenu = ItemStackUtils.createSimpleItem(Material.BARRIER, ChatMessageUtils.translateColors(localization.getString("inventory","menu_exit")), Collections.singletonList(ChatMessageUtils.translateColors("&eCool af 3.")));

        inventory.setItem(10, createShop);
        inventory.setItem(13, createVault);
        inventory.setItem(16, viewShops);
        inventory.setItem(31, closeMenu);
    }
}
