package cz.lunari.lunarimarket.commands;

import cz.lunari.lunarimarket.managers.InventoryManager;
import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.inventory.inventories.MainMenuInventory;
import org.bukkit.entity.Player;

public class MenuCommand implements ICommand {
    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public String getDescription() {
        return "Opens main menu";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/lunarimarket menu";
    }

    @Override
    public void execute(Player player, String[] args) {
        InventoryManager handler = new InventoryManager();
        MainMenuInventory menu = new MainMenuInventory(handler.getInventoryOwner(player));
        menu.openInventoryMenu();
    }
}
