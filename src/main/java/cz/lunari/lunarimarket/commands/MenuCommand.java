package cz.lunari.lunarimarket.commands;

import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.inventory.inventories.MainMenuInventory;
import cz.lunari.lunarimarket.managers.InventoryManager;
import org.bukkit.command.CommandSender;
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
    public Boolean runInConsole() {
        return false;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        InventoryManager handler = new InventoryManager();
        MainMenuInventory menu = new MainMenuInventory(handler.getInventoryOwner((Player) sender));
        menu.openInventoryMenu();
    }
}
