package cz.lunari.lunarimarket.utils;

import org.bukkit.entity.Player;

public class InventoryHolderUtils {

    private Player owner;

    public InventoryHolderUtils(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}
