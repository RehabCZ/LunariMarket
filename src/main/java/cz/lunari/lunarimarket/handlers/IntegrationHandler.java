package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.interfaces.IIntegration;
import cz.lunari.lunarimarket.plugins.WGIntegration;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;

import java.util.ArrayList;

public class IntegrationHandler {

    private final ArrayList<IIntegration> IIntegration = new ArrayList<>();

    public IntegrationHandler() {
        IIntegration.add(new WGIntegration());
    }

    public void initIntegration() {
        for (int i = 0; i < getIntegration().size(); i++) {
            if (getIntegration().get(i).getInstance() != null && getIntegration().get(i).isEnabled()) {
                getIntegration().get(i).execute();
                ChatMessageUtils.logConsole(
                        "&7Plugin hook&2 " + getIntegration().get(i).getName() + " &7was successfully initialized."
                );
            }
        }
    }

    public ArrayList<IIntegration> getIntegration() {
        return IIntegration;
    }

}
