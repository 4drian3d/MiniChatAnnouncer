package net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dreamerzero.MiniChatAnnouncer.Announcer;
import net.dreamerzero.MiniChatAnnouncer.utils.MiniMessageUtil;
import net.dreamerzero.MiniChatAnnouncer.utils.SoundUtil;
import static net.dreamerzero.MiniChatAnnouncer.utils.PlaceholderUtil.replacePlaceholders;

public class SendChatCommand implements CommandExecutor {
    private Announcer plugin;
	public SendChatCommand(Announcer plugin) {
		this.plugin = plugin;
	}

    // Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Permission Check
        if (!(sender.hasPermission("announcer.chat.send"))){
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.no-permission")));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.only-player")));
            return false;
        }

        // Get the player
        var playerObjetive = Bukkit.getPlayer(args[0]);

        var serverplayers = Bukkit.getOnlinePlayers();

        if (!(serverplayers.contains(playerObjetive))){
            // Send an error message to the sender using the command.
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.player-not-found")));
                return false;
        }

        // Concatenate the arguments provided by the command sent.
        StringBuilder chattext = new StringBuilder();
        for (byte i = 1; i < args.length; i++) {
            chattext = chattext.append(" ");
            chattext = chattext.append(args[i]); 
        }
        
        // Convert StringBuilder to String, Component is not compatible :nimodo:
        var chattoparse = chattext.toString();

        if (sender instanceof Player) {
            var player = (Player) sender;
            // Send to all
            playerObjetive.sendMessage(
                MiniMessageUtil.parse(chattoparse, replacePlaceholders(player, playerObjetive)));
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.successfully")));
        } else {
            // Send to all
            playerObjetive.sendMessage(
                MiniMessageUtil.parse(chattoparse, replacePlaceholders(playerObjetive)));
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.successfully")));
        }
        

        var soundtoplay = plugin.getConfig().getString("sounds.chat.sound-id", "entity.experience_orb.pickup");
        var soundEnabled = plugin.getConfig().getBoolean("sounds.chat.enabled", true);
        float volume = plugin.getConfig().getInt("sounds.chat.volume", 10);
        float pitch = plugin.getConfig().getInt("sounds.chat.pitch", 2);
        
        if (soundEnabled) {
            // Play the sound
            SoundUtil.playSound(
                soundtoplay, 
                playerObjetive, 
                volume, 
                pitch
            );
        }

        return true;
    }
}
