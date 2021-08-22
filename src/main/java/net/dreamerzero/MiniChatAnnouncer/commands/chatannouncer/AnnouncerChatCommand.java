package net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.dreamerzero.MiniChatAnnouncer.Announcer;
import net.dreamerzero.MiniChatAnnouncer.utils.MiniMessageUtil;
import net.dreamerzero.MiniChatAnnouncer.utils.SoundUtil;

import net.kyori.adventure.audience.Audience;

public class AnnouncerChatCommand implements CommandExecutor {
    private Announcer plugin;
	public AnnouncerChatCommand(Announcer plugin) {
		this.plugin = plugin;
	}

    // The audience that will receive the chat will be all the players on the server.
    public Audience audience = Bukkit.getServer();

    // Default Sound
    String soundtoplay = "entity.experience_orb.pickup";
    // Is Enabled?
    Boolean soundEnabled = true;
    // Volume
    float volume = 10f;
    // Pitch
    float pitch = 2f;

    // Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Permission Check
        if (!(sender.hasPermission("announcer.chat.global"))){
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.no-permission")));
            return true;
        }

        // Concatenate the arguments provided by the command sent.
        StringBuilder chattext = new StringBuilder();
        for (byte i = 0; i < args.length; i++) {
            chattext = chattext.append(" ");
            chattext = chattext.append(args[i]); 
        }

        // Convert StringBuilder to String, Component is not compatible :nimodo:
        String chattoparse = chattext.toString();
        
        // Send to all
        audience.sendMessage(
            MiniMessageUtil.parse(chattoparse));
        sender.sendMessage(
            MiniMessageUtil.parse(
                plugin.getConfig().getString("messages.chat.successfully")));

        soundtoplay = plugin.getConfig().getString("sounds.chat.sound-id");
        soundEnabled = plugin.getConfig().getBoolean("sounds.chat.enabled");
        volume = plugin.getConfig().getInt("sounds.chat.volume");
        pitch = plugin.getConfig().getInt("sounds.chat.pitch");

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
