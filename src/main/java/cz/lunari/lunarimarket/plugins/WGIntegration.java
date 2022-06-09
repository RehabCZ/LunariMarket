package cz.lunari.lunarimarket.plugins;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.interfaces.IIntegration;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class WGIntegration implements IIntegration {

    @Getter
    private StateFlag shopFlag;

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
    public boolean isEnabled() {
        return LunariMarket.getInstance().getConfigManager().getBoolean("integration.WorldGuard");
    }

    @Override
    public void execute() {
        FlagRegistry flagReg = WorldGuard.getInstance().getFlagRegistry();

        try {
            StateFlag flag = new StateFlag("lunarimarket", false);
            flagReg.register(flag);

            shopFlag = flag;
        } catch (FlagConflictException e) {
            e.printStackTrace();
        }
    }
}
