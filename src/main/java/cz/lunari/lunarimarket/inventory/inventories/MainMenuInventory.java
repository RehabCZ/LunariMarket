package cz.lunari.lunarimarket.inventory.inventories;

import cz.lunari.lunarimarket.inventory.InventoryMenu;
import cz.lunari.lunarimarket.utils.ChatUtils;
import cz.lunari.lunarimarket.utils.ItemStackUtils;
import cz.lunari.lunarimarket.utils.InventoryHolderUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class MainMenuInventory extends InventoryMenu {

    public MainMenuInventory(InventoryHolderUtils invUtils) {
        super(invUtils);
    }

    @Override
    public String getInventoryName() {
        return ChatUtils.translateColors("&6&lLunariMarket &7GUI");
    }

    @Override
    public Integer getInventorySlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        switch (e.getCurrentItem().getType()) {
            case BARREL -> {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatUtils.translateColors("&7You clicked shop Creation"));
            }
            case NAME_TAG -> {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatUtils.translateColors("&7You clicked vault Creation"));
            }
            case PLAYER_HEAD -> {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatUtils.translateColors("&7You clicked view your shops"));
            }
        }


    }

    @Override
    public void setMenuItems() {

        ItemStack createItem = ItemStackUtils.createSimpleItem(Material.BARREL, ChatUtils.translateColors("&a&lCreate shop"), Collections.singletonList(ChatUtils.translateColors("&eCool af.")));
        ItemStack createVault = ItemStackUtils.createSimpleItem(Material.NAME_TAG, ChatUtils.translateColors("&a&lCreate vault"), Collections.singletonList(ChatUtils.translateColors("&eCool af hehehe.")));
        ItemStack viewShops = ItemStackUtils.createPlayerSkull(this.inventoryHolderUtils.getOwner(), ChatUtils.translateColors("&6Loool"), Collections.singletonList(ChatUtils.translateColors("&7Mein god")));

        inventory.setItem(10, createItem);
        inventory.setItem(13, createVault);
        inventory.setItem(16, viewShops);
    }
}
