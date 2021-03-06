package me.libraryaddict.Hungergames.Commands;

import me.libraryaddict.Hungergames.Hungergames;
import me.libraryaddict.Hungergames.Managers.ChatManager;
import me.libraryaddict.Hungergames.Managers.PlayerManager;
import me.libraryaddict.Hungergames.Types.HungergamesApi;
import me.libraryaddict.Hungergames.Types.Gamer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GoTo implements CommandExecutor {
    private PlayerManager pm = HungergamesApi.getPlayerManager();
    private Hungergames hg = HungergamesApi.getHungergames();
    private ChatManager cm = HungergamesApi.getChatManager();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Gamer gamer = pm.getGamer(sender.getName());
        if (!gamer.isAlive()) {
            if (args.length > 0) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Bukkit.getPlayerExact(sender.getName()).teleport(
                            sender.getServer().getPlayer(args[0]).getLocation().add(0, 0.1, 0));
                    return true;
                } else if (args[0].equalsIgnoreCase(cm.getCommandGotoNameOfFeast())) {
                    if (hg.feastLoc.getBlockY() > 0) {
                        Bukkit.getPlayerExact(sender.getName()).teleport(
                                hg.feastLoc.getWorld().getHighestBlockAt(hg.feastLoc).getLocation().clone().add(0.5, 1, 0.5));
                    } else
                        sender.sendMessage(cm.getCommandGotoFeastFailed());
                    return true;
                } else
                    sender.sendMessage(cm.getCommandGotoPlayerDoesntExist());
            } else
                sender.sendMessage(cm.getCommandGotoNotEnoughArgs());
        } else
            sender.sendMessage(cm.getCommandGotoNotSpectator());
        return true;
    }
}
