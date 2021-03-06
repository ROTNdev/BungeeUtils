package me.aventium.bungeeutils.commands;

import me.aventium.bungeeutils.BungeeUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Message extends Command {

    BungeeUtils utils;

    public Message(BungeeUtils utils) {
        super("message", "bungeeutils.message", new String[]{ "tell", "msg" });
        this.utils = utils;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 2) {
            utils.sendMessage(sender, "&c/message <player> <message>");
            return;
        }

        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);

        if(player == null) {
            utils.sendMessage(sender, "&cPlayer '" + args[0] + "' not found!");
            return;
        }

        StringBuilder message = new StringBuilder();
        for(int i = 1; i < args.length; i++) {
            message.append(args[i] + " ");
        }

        String msg = message.toString().trim();

        utils.sendMessage(sender, "&7[me -> " + player.getName() + "] " + msg);
        utils.sendMessage(player, "&7[" + sender.getName() + " -> me] " + msg);

        utils.getMessages().put(sender.getName(), player.getName());
        utils.getMessages().put(player.getName(), sender.getName());
    }
}
