package org.camobiwon.boneworksbot;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.ExecutionException;

public class ChatContains implements MessageCreateListener {

    //If chat message contains keyphrase, then respond
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        if (event.getMessageContent().toLowerCase().contains("bone work")) {
            try {
                event.getMessage().addReaction(":BONEWORK:584986092990758914").get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ChatLog.log(event, "Reaction Added");
        }

        if (event.getMessageContent().toLowerCase().contains("tech demo")) {
            try {
                event.getMessage().addReaction(":TechDemo:610603749823741963").get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ChatLog.log(event, "Reaction Added");
        }
    }
}
