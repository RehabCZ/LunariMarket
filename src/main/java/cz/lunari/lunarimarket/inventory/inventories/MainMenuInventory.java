package cz.lunari.lunarimarket.inventory.inventories;

import cz.lunari.lunarimarket.inventory.InventoryMenu;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import cz.lunari.lunarimarket.utils.ItemStackUtils;
import cz.lunari.lunarimarket.objects.InventoryOwner;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class MainMenuInventory extends InventoryMenu {

    public MainMenuInventory(InventoryOwner owner) {
        super(owner);
    }

    @Override
    public String getInventoryName() {
        return ChatMessageUtils.translateColors("&6&lLunariMarket &7GUI");
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
            case BARREL -> {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatMessageUtils.translateColors("&7You clicked shop Creation"));
            }

            case NAME_TAG -> {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatMessageUtils.translateColors("&7You clicked vault Creation"));
            }

            case PLAYER_HEAD -> {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatMessageUtils.translateColors("&7You clicked view your shops"));
            }

            case BARRIER -> e.getWhoClicked().closeInventory();
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack createItem = ItemStackUtils.createSimpleItem(Material.BARREL, ChatMessageUtils.translateColors("&a&lCreate shop"), Collections.singletonList(ChatMessageUtils.translateColors("&eCool af.")));
        ItemStack createVault = ItemStackUtils.createSimpleItem(Material.NAME_TAG, ChatMessageUtils.translateColors("&a&lCreate vault"), Collections.singletonList(ChatMessageUtils.translateColors("&eCool af hehehe.")));
        ItemStack viewShops = ItemStackUtils.createPlayerSkull(inventoryOwner.getOwner(), ChatMessageUtils.translateColors("&6Loool"), Collections.singletonList(ChatMessageUtils.translateColors("&7Mein god")));
        ItemStack closeMenu = ItemStackUtils.createSimpleItem(Material.BARRIER, ChatMessageUtils.translateColors("&c&lExit menu"), Collections.singletonList(ChatMessageUtils.translateColors("&eCool af 3.")));

        inventory.setItem(10, createItem);
        inventory.setItem(13, createVault);
        inventory.setItem(16, viewShops);
        inventory.setItem(31, closeMenu);
    }
}
