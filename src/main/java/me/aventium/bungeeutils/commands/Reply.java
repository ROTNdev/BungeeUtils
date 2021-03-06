package me.aventium.bungeeutils.commands;

import me.aventium.bungeeutils.BungeeUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by student on 9/11/14.
 */
public class Reply extends Command {

    BungeeUtils utils;
    
    public Reply(BungeeUtils utils) {
        super("reply", "bungeeutils.message", "r");
        this.utils = utils;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 1) {
            utils.sendMessage(sender, "&c/reply <message>");
            return;
        }

        if(!utils.getMessages().containsKey(sender.getName())) {
            utils.sendMessage(sender, "&cYou haven't messaged anyone!");
            return;
        }

        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(utils.getMessages().get(sender.getName()));

        if(player == null) {
            utils.sendMessage(sender, "&cPlayer '" + args[0] + "' not found!");
            return;
        }

        StringBuilder message = new StringBuilder();
        for(String arg : args) {
            message.append(arg + " ");
        }

        String msg = message.toString().trim();

        utils.sendMessage(sender, "&7[me -> " + player.getName() + "] " + msg);
        utils.sendMessage(player, "&7[" + sender.getName() + " -> me] " + msg);

        utils.getMessages().put(player.getName(), sender.getName());
    }

}
