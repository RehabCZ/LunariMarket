package cz.lunari.lunarimarket.managers;

import com.google.common.collect.Lists;
import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
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
        commands.add(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatMessageUtils.translateColors("&8&l>&m============&8&l[&6&lLunari Market&8&l]&m============&8&l<"));

            for (ICommand iCommand : commands) {
                player.sendMessage(iCommand.getSyntax() + " - " + iCommand.getDescription());
            }

            player.sendMessage(ChatMessageUtils.translateColors("&8&l>&m=====================================&8&l<"));

            return true;
        }

        ICommand iCommand = getCommand(args[0]);

        if (iCommand == null || !player.hasPermission(iCommand.getPermission())) {
            player.sendMessage(ChatMessageUtils.translateColors("&cCommand not found or you lack permissions to use that!"));
            return false;
        }

        iCommand.execute(player, args);

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> listCommands = Lists.newArrayList();

        if (args.length == 0) {
            return listCommands;
        }

        for (ICommand iCommand : commands) {
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
}
