package net.dreamerzero.MiniChatAnnouncer.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiniMessageUtil {
    /* 
    Component that parses the message
    with the MiniMessage format.
    */
    public static Component parse(
        final String message) {

        return MiniMessage.get().parse(message);
    }
}
