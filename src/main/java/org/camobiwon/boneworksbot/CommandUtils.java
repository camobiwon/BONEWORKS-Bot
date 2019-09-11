package org.camobiwon.boneworksbot;

import org.javacord.api.event.message.MessageCreateEvent;

public class CommandUtils {

    //Check if user is admin
    public static boolean isAdmin(MessageCreateEvent event) {
        boolean isAdmin = Main.adminIDs.contains((event.getMessageAuthor().getId()));
        if (!isAdmin) {
            event.getChannel().sendMessage("<:AlexNo:617150461127950357>");
            ChatLog.log(event);
        }
        return isAdmin;
    }
}
