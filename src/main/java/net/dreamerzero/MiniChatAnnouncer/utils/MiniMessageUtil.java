package net.dreamerzero.MiniChatAnnouncer.utils;

import java.util.List;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;

public class MiniMessageUtil {
    /* 
    Component that parses the message
    with the MiniMessage format.
    */
    public static Component parse(
        final String message) {

        return MiniMessage.get().parse(message);
    }

    public static Component parse(
        final String message, Template template) {

        return MiniMessage.get().parse(message, template);
    }

    public static Component parse(
        final String message, List<Template> template) {

        return MiniMessage.get().parse(message, template);
    }
}
