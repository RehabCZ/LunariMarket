package cz.lunari.lunarimarket.plugins;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import cz.lunari.lunarimarket.handlers.ConfigHandler;
import cz.lunari.lunarimarket.interfaces.IPluginHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

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

}
