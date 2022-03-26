package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.interfaces.IPluginHook;
import cz.lunari.lunarimarket.plugins.WGHook;
import cz.lunari.lunarimarket.LunariMarket;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class HooksHandler {

    private ArrayList<IPluginHook> IPluginHook = new ArrayList<>();

    public HooksHandler() {
        IPluginHook.add(new WGHook());
    }

    public void initHooks()
    {
        for(int i = 0; i < getHook().size(); i++){
            if(getHook().get(i).plInstance() != null && getHook().get(i).isEnabled()) {
                getHook().get(i).loadHook();
                LunariMarket.getInstance().getServer().getConsoleSender().sendMessage(
                        ChatColor.GRAY + "Plugin hook " +
                        ChatColor.GREEN + getHook().get(i).plName() +
                        ChatColor.GRAY +" was successfully initialized."
                );
            }
        }
    }

    public ArrayList<IPluginHook> getHook(){ return IPluginHook; }

}
