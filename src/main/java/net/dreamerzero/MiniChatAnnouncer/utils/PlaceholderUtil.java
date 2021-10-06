package net.dreamerzero.MiniChatAnnouncer.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.Template;

import static net.kyori.adventure.text.Component.*;

public class PlaceholderUtil {
    /**
     * Replace Placeholders in Title/ActionBar
     * for the context of Console
     * @return Placeholders for console
     */
    public static List<Template> replacePlaceholders() {
        final String mspt = String.valueOf(Bukkit.getAverageTickTime()/20).substring(0, 3);
        final String tps = String.valueOf(Bukkit.getTPS()[0]).substring(0, 4);

        final List<Template> templates = List.of(
            Template.of("online", text(Bukkit.getServer().getOnlinePlayers().size())),
            Template.of("player", text("CONSOLA")),
            Template.of("mspt", text(String.valueOf(mspt))),
            Template.of("tps", text(String.valueOf(tps))));
        return templates;
    }
    /**
     * Replace Placeholders in ChatAnnouncers
     * for player sender
     * @param player
     * @return Placeholders for sender player
     */
    public static List<Template> replacePlaceholders(Player player) {
        final String mspt = String.valueOf(Bukkit.getAverageTickTime()/20).substring(0, 3);
        final String tps = String.valueOf(Bukkit.getTPS()[0]).substring(0, 4);

        final List<Template> templates = List.of(
            Template.of("name", text(player.getName())), 
            Template.of("ping", text(player.getPing())),
            Template.of("online", text(Bukkit.getServer().getOnlinePlayers().size())),
            Template.of("world", text(player.getWorld().getName())),
            Template.of("player", text(player.getName())),
            Template.of("mspt", text(String.valueOf(mspt))),
            Template.of("tps", text(String.valueOf(tps))),
            Template.of("item", text("item", NamedTextColor.BLUE, TextDecoration.UNDERLINED).hoverEvent(player.getInventory().getItemInMainHand())));
        return templates;
    }
    /**
     * Replace Placeholder in Chat Announcers
     * to Player or PlayerObjetive
     * @param player
     * @param otherPlayer
     * @return Placeholders for sender player and the playerobjetive
     */
    public static List<Template> replacePlaceholders(Player player, Player otherPlayer) {
        final String mspt = String.valueOf(Bukkit.getAverageTickTime()/20).substring(0, 3);
        final String tps = String.valueOf(Bukkit.getTPS()[0]).substring(0, 4);

        final List<Template> templates = List.of(
            Template.of("name", text(player.getName())), 
            Template.of("ping", text(player.getPing())),
            Template.of("online", text(Bukkit.getServer().getOnlinePlayers().size())),
            Template.of("world", text(player.getWorld().getName())),
            Template.of("othername", text(otherPlayer.getName())),
            Template.of("otherping", text(otherPlayer.getPing())),
            Template.of("otherworld", text(otherPlayer.getName())),
            Template.of("mspt", text(String.valueOf(mspt))),
            Template.of("tps", text(String.valueOf(tps))),
            Template.of("item", translatable(player.getInventory().getItemInMainHand().translationKey()).hoverEvent(player.getInventory().getItemInMainHand().asHoverEvent())),
            Template.of("otheritem", translatable(otherPlayer.getInventory().getItemInMainHand().translationKey()).hoverEvent(otherPlayer.getInventory().getItemInMainHand().asHoverEvent())));
        return templates;
    }
}
