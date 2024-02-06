package cz.lunari.lunarimarket.managers;

import com.google.common.collect.Lists;
import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final List<ICommand> commands = Lists.newArrayList();

    public CommandManager(JavaPlugin plugin, String cmdName) {
        Objects.requireNonNull(plugin.getCommand(cmdName)).setExecutor(this);
    }

    public void addCommand(ICommand command) {
        ChatMessageUtils.logConsole("&7Command &2" + command.getName() + "&7 was successfully registered.");
        commands.add(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (args.length == 0 || getCommand(args[0]) == null) {
            showHelp(sender);
            return true;
        }

        ICommand iCommand = getCommand(args[0]);

        if (sender instanceof ConsoleCommandSender) {
            if (iCommand.runInConsole()) {
                iCommand.execute(sender, args);
            } else
                sender.sendMessage(ChatMessageUtils.translateColors("&cThis command cannot run in console!"));
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(iCommand.getPermission())) {
                iCommand.execute(sender, args);
            } else
                player.sendMessage(ChatMessageUtils.translateColors("&cYou don't have permission to use that!"));
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> listCommands = Lists.newArrayList();

        if (args.length == 0) {
            return listCommands;
        }

        for (ICommand iCommand : commands) {
            if (sender.hasPermission(iCommand.getPermission()))
                listCommands.add(iCommand.getName());
        }

        return listCommands;
    }

    public ICommand getCommand(String name) {
        for (ICommand iCommand : commands) {
            if (iCommand.getName().equalsIgnoreCase(name)) {
                return iCommand;
            }
        }

        return null;
    }

    public void showHelp(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatMessageUtils.translateColors("&8&l>&m============&8&l[&6&lLunari Market&8&l]&m============&8&l<"));

            for (ICommand iCommand : commands) {
                if (player.hasPermission(iCommand.getPermission()))
                    player.sendMessage(iCommand.getSyntax() + " - " + iCommand.getDescription());
            }

            player.sendMessage(ChatMessageUtils.translateColors("&8&l>&m=====================================&8&l<"));
        }

        if (sender instanceof ConsoleCommandSender) {
            for (ICommand iCommand : commands) {
                if (iCommand.runInConsole())
                    sender.sendMessage(iCommand.getSyntax() + " - " + iCommand.getDescription());
            }
        }
    }
}
