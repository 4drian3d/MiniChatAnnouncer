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

import net.kyori.adventure.audience.Audience;

public class AnnouncerChatCommand implements CommandExecutor {
    private Announcer plugin;
	public AnnouncerChatCommand(Announcer plugin) {
		this.plugin = plugin;
	}

    // The audience that will receive the chat will be all the players on the server.
    public Audience audience = Bukkit.getServer();

    // Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Permission Check
        if (!(sender.hasPermission("announcer.chat.global"))){
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.no-permission")));
            return true;
        }

        Player player = (Player) sender;

        // Concatenate the arguments provided by the command sent.
        StringBuilder chattext = new StringBuilder();
        for (byte i = 0; i < args.length; i++) {
            chattext = chattext.append(" ");
            chattext = chattext.append(args[i]); 
        }

        // Convert StringBuilder to String, Component is not compatible :nimodo:
        var chattoparse = chattext.toString();
        
        // Send to all
        audience.sendMessage(
            MiniMessageUtil.parse(chattoparse, replacePlaceholders(player)));
        sender.sendMessage(
            MiniMessageUtil.parse(
                plugin.getConfig().getString("messages.chat.successfully")));

        var soundtoplay = plugin.getConfig().getString("sounds.chat.sound-id", "entity.experience_orb.pickup");
        var soundEnabled = plugin.getConfig().getBoolean("sounds.chat.enabled", true);
        float volume = plugin.getConfig().getInt("sounds.chat.volume", 10);
        float pitch = plugin.getConfig().getInt("sounds.chat.pitch", 2);

        if (soundEnabled) {
            // Play the sound
            SoundUtil.playSound(
                soundtoplay, 
                audience, 
                volume, 
                pitch
            );
        }
        return true;
    }
}
