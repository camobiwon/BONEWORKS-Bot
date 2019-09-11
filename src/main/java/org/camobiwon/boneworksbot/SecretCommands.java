package org.camobiwon.boneworksbot;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class SecretCommands implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        //Custom commands
        switch (event.getMessageContent().toLowerCase()) {

            case "!secretcommand":
                event.getChannel().sendMessage("Wow, a secret command");
                ChatLog.log(event);
                break;


                /*
                In this class, you can add your own secret commands
                This class will be put in .gitignore to prevent any people from trying to see what the commands may be
                */
        }
    }
}