package cz.lunari.lunarimarket.managers;

import com.google.common.collect.Lists;
import cz.lunari.lunarimarket.interfaces.IIntegration;
import cz.lunari.lunarimarket.plugins.WGIntegration;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;

import java.util.List;

public class IntegrationManager {

    private final List<IIntegration> integrations = Lists.newArrayList();

    public IntegrationManager() {
        addIntegration(new WGIntegration());

        initIntegration();
    }

    private void addIntegration(IIntegration integration) {
        integrations.add(integration);
    }

    public void initIntegration() {
       for (IIntegration integration : integrations) {
            if (integration.getInstance() != null && integration.isEnabled()) {
                integration.execute();

                ChatMessageUtils.logConsole(
                        "&7Plugin hook&2 " + integration.getName() + " &7was successfully initialized."
                );
            }
        }
    }
}
