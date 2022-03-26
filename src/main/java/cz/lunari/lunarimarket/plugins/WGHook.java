package cz.lunari.lunarimarket.plugins;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import cz.lunari.lunarimarket.handlers.ConfigHandler;
import cz.lunari.lunarimarket.interfaces.IPluginHook;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class WGHook implements IPluginHook {

    private StateFlag SHOP_FLAG;

    @Override
    public Plugin plInstance() {
        return Bukkit.getPluginManager().getPlugin("WorldGuard");
    }

    @Override
    public String plName() { return "WorldGuard"; }

    @Override
    public String plVersion() {
        return WorldGuard.getVersion();
    }

    @Override
    public Boolean isEnabled() {
        return ConfigHandler.getConfigBool("WorldGuardHook");
    }

    @Override
    public void loadHook() {
        FlagRegistry flagReg = WorldGuard.getInstance().getFlagRegistry();

        try
        {
            StateFlag flag = new StateFlag("lunarimarket",false);
            flagReg.register(flag);
            SHOP_FLAG = flag;
        } catch (FlagConflictException e){ e.printStackTrace(); }
    }

    public boolean canCreateShop(Block block)
    {
        AtomicBoolean isShopLoc = new AtomicBoolean(false);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(new BukkitWorld(block.getWorld()));

        if(SHOP_FLAG != null){
            if(regions != null){
                regions.getRegions().forEach((id, region) -> {
                    if(region.getId().equalsIgnoreCase(ProtectedRegion.GLOBAL_REGION) || region.contains(block.getX(), block.getY(), block.getZ())) {
                        if((region.getFlag(SHOP_FLAG) != null) && Objects.equals(region.getFlag(SHOP_FLAG), StateFlag.State.ALLOW))
                            isShopLoc.set(true);
                    }
                });
            }
        }
        else { isShopLoc.set(true); }
        return isShopLoc.get();
    }
}
