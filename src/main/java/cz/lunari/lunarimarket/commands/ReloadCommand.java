package cz.lunari.lunarimarket.commands;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements ICommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads plugin configuration";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/lunarimarket reload";
    }

    @Override
    public Boolean runInConsole() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        //TODO: In the future we will need to reload storage (database) and some kind of shop manager
        LunariMarket.getInstance().getConfiguration().reload();
        LunariMarket.getInstance().getLocalization().reload();

        sender.sendMessage(ChatMessageUtils.translateColors("&2Config file was successfully reloaded!"));
    }
}
