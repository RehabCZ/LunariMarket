package cz.lunari.lunarimarket.plugins;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import cz.lunari.lunarimarket.config.Config;
import cz.lunari.lunarimarket.interfaces.IPluginHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class WGHook implements IPluginHook {


    private StateFlag SHOP_FLAG;

    @Override
    public Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin(getName());
    }

    @Override
    public String getName() {
        return "WorldGuard";
    }

    @Override
    public String getVersion() {
        return WorldGuard.getVersion();
    }

    @Override
    public Boolean isEnabled() {
        return Config.getConfigBool("WorldGuardHook");
    }

    @Override
    public void execute() {
        FlagRegistry flagReg = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("lunarimarket", false);
            flagReg.register(flag);
            SHOP_FLAG = flag;
        } catch (FlagConflictException e) {
            e.printStackTrace();
        }
    }

}
