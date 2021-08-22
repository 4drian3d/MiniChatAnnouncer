package net.dreamerzero.MiniChatAnnouncer;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer.SelfChatCommand;
import net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer.SendChatCommand;
import net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer.AnnouncerChatCommand;
import net.dreamerzero.MiniChatAnnouncer.utils.MiniMessageUtil;
import net.dreamerzero.MiniChatAnnouncer.commands.chatannouncer.WorldChatCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class Announcer extends JavaPlugin {
	// Component to send the server name: Peruviankkit... in color... in console
	static final TextComponent pvknet = 
		Component.text("Peru", 
			NamedTextColor.DARK_RED)
		.append(Component.text("vian", 
				NamedTextColor.WHITE))
		.append(Component.text("kkit", 
				NamedTextColor.DARK_RED))
		.append(Component.text(" Network", 
				NamedTextColor.GREEN));
	// Plugin Name with color
	static final Component eventannouncertext = 
		MiniMessageUtil.parse(
			"<gradient:yellow:red>MiniChatAnnouncer</gradient>");
	// Line
	static final TextComponent linelong = 
		Component.text("----------------------", 
			NamedTextColor.DARK_GRAY);
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(linelong);
		Bukkit.getConsoleSender().sendMessage(
			Component.text("Enabling ", 
				NamedTextColor.AQUA)
			.append(eventannouncertext));
		Bukkit.getConsoleSender().sendMessage(pvknet);
		Bukkit.getConsoleSender().sendMessage(linelong);
		pluginConfiguration();
		commandRegister();
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(linelong);
		Bukkit.getConsoleSender().sendMessage(
			Component.text("Disabling ", 
				NamedTextColor.AQUA)
			.append(eventannouncertext));
		Bukkit.getConsoleSender().sendMessage(pvknet);
		Bukkit.getConsoleSender().sendMessage(linelong);
	}
	
	// Registration of the commands that the plugin provides
	public void commandRegister() {
		// Chat Commands
		getCommand("announcechat")
			.setExecutor(new AnnouncerChatCommand(this));
		getCommand("testchat")
			.setExecutor(new SelfChatCommand(this));
		getCommand("worldchat")
			.setExecutor(new WorldChatCommand(this));
		getCommand("sendchat")
			.setExecutor(new SendChatCommand(this));
	}

	public void pluginConfiguration() {
		File config = new File(this.getDataFolder(), "config.yml");
		if(!config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
	}
}
