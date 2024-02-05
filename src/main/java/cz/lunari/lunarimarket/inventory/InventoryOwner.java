package cz.lunari.lunarimarket.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public class InventoryOwner {

    private final Player owner;
}
