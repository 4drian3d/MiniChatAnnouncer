package net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dreamerzero.MiniChatAnnouncer.Announcer;
import net.dreamerzero.MiniChatAnnouncer.utils.MiniMessageUtil;
import net.dreamerzero.MiniChatAnnouncer.utils.SoundUtil;
import static net.dreamerzero.MiniChatAnnouncer.utils.PlaceholderUtil.replacePlaceholders;
import net.kyori.adventure.audience.Audience;

public class WorldChatCommand implements CommandExecutor {
    private Announcer plugin;
	public WorldChatCommand(Announcer plugin) {
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

    // Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // It will send an chat to the world in which the command is executed, 
        // it makes no sense for the console to execute it.
        if (!(sender instanceof Player)) {
            plugin.getLogger().info("The console cannot execute this command.");
            return false;
        }

        // Player
        Player player = (Player) sender;

        // Permission Check
        if (!(player.hasPermission("announcer.chat.world"))){
            sender.sendMessage(
                MiniMessageUtil.parse(
                    plugin.getConfig().getString("messages.chat.no-permission")));
            return true;
        }

        // Get the world in which the player is located
        Audience audience = player.getWorld();

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
            MiniMessageUtil.parse(chattoparse, replacePlaceholders(player)));
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
