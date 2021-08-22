package net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dreamerzero.MiniChatAnnouncer.Announcer;
import net.dreamerzero.MiniChatAnnouncer.utils.MiniMessageUtil;
import net.dreamerzero.MiniChatAnnouncer.utils.SoundUtil;

public class SelfChatCommand implements CommandExecutor {
    private Announcer plugin;
	public SelfChatCommand(Announcer plugin) {
		this.plugin = plugin;
	}

    // Default Sound
    String soundtoplay = "entity.experience_orb.pickup";
    // Is Enabled?
    Boolean soundEnabled = true;
    // Volume
    float volume = 10f;
    // Pitch
    float pitch = 2f;

    //Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // It will send an chat to the one who executes the command, 
        // it makes no sense for the console to execute it.
        if (!(sender instanceof Player)) {
            plugin.getLogger().info("The console cannot execute this command.");
            return false;
        }

        // Permission Check
        if (!(sender.hasPermission("announcer.chat.test"))){
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
        
        // Send to sender
        sender.sendMessage(
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
                sender, 
                volume, 
                pitch
            );
        }

        return true;
    }
}