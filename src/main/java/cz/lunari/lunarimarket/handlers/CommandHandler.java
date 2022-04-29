package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.commands.AboutCommand;
import cz.lunari.lunarimarket.commands.HelpCommand;
import cz.lunari.lunarimarket.commands.MenuCommand;
import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {

    private final ArrayList<ICommand> commands = new ArrayList<>();

    public CommandHandler() {
        commands.add(new HelpCommand());
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
            } else {
                player.sendMessage(ChatMessageUtils.translateColors("&8&l>&m============&8&l[&6&lLunari Market&8&l]&m============&8&l<"));
                for (int i = 0; i < getCommand().size(); i++) {
                    player.sendMessage(getCommand().get(i).getSyntax() + " - " + getCommand().get(i).getDescription());
                }
                player.sendMessage(ChatMessageUtils.translateColors("&8&l>&m=====================================&8&l<"));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            List<String> listCommands = new ArrayList<>();
            for (int i = 0; i < getCommand().size(); i++) {
                listCommands.add(getCommand().get(i).getName());
            }
            return listCommands;
        }
        return null;
    }

    public ArrayList<ICommand> getCommand() {
        return commands;
    }
}
