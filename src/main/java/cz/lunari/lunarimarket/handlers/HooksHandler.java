package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.interfaces.IPluginHook;
import cz.lunari.lunarimarket.plugins.HDHook;
import cz.lunari.lunarimarket.plugins.WGHook;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;

import java.util.ArrayList;

public class HooksHandler {

    private final ArrayList<IPluginHook> IPluginHook = new ArrayList<>();

    public HooksHandler() {
        IPluginHook.add(new WGHook());
        IPluginHook.add(new HDHook());
    }

    public void initHooks() {
        for (int i = 0; i < getHook().size(); i++) {
            if (getHook().get(i).getInstance() != null && getHook().get(i).isEnabled()) {
                getHook().get(i).execute();
                ChatMessageUtils.logConsole(
                        "&7Plugin hook&2 " + getHook().get(i).getName() + " &7was successfully initialized."
                );
            }
        }
    }

    public ArrayList<IPluginHook> getHook() {
        return IPluginHook;
    }

}
