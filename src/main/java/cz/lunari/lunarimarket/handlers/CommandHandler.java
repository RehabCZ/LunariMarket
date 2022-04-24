package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.commands.AboutCommand;
import cz.lunari.lunarimarket.commands.MenuCommand;
import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.utils.ChatUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandHandler implements CommandExecutor {

    private final ArrayList<ICommand> commands = new ArrayList<>();

    public CommandHandler() {
        commands.add(new AboutCommand());
        commands.add(new MenuCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                for (int i = 0; i < getCommand().size(); i++) {
                    if (args[0].equalsIgnoreCase(getCommand().get(i).getName())
                            && player.hasPermission(getCommand().get(i).getPermission())) {
                        getCommand().get(i).execute(player, args);
                    }
                }
            } else if (args.length == 0 || args[0].equals("help")) {
                player.sendMessage(ChatUtils.translateColors("&8&l>&m============&8&l[&6&lLunari Market&8&l]&m============&8&l<"));
                for (int i = 0; i < getCommand().size(); i++) {
                    player.sendMessage(getCommand().get(i).getSyntax() + " - " + getCommand().get(i).getDescription());
                }
                player.sendMessage(ChatUtils.translateColors("&8&l>&m=====================================&8&l<"));
            }
        }
        return true;
    }

    public ArrayList<ICommand> getCommand() {
        return commands;
    }
}
