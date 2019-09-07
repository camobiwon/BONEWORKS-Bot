package org.camobiwon.boneworksbot;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;

public class Commands implements CommandExecutor {

    //Eventually we'll switch over to this command style
    /*
    //Check if user is admin
    private boolean isAdmin(User user, TextChannel textChannel) {

        boolean isAdmin = Main.adminIDs.contains((user.getId()));
        if (!isAdmin) {
            textChannel.sendMessage("<:AlexNo:617150461127950357>");
            ChatLog.log(event);
        }
        return isAdmin;
    }

    //Admin commands
    @Command(aliases = "!shutdown", description = "Shuts down the bot")
    public void onShutdownCommand() {
        if(isAdmin(event)) {

        }
    }

    //Custom commands
    @Command(aliases = "!ping", description = "Pong!")
    public String onPingCommand() {
        return "Pong!";
    }
    */
}