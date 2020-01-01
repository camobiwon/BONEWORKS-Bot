package org.camobiwon.boneworksbot;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.util.concurrent.ExecutionException;

public class Voting implements MessageCreateListener {

    //If chat message contains keyphrase, then respond
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        if (event.getChannel().getId() == Configuration.voteID || event.getChannel().getId() == 661042292588544000L) {
            System.out.println("msg sent");
            try {
                event.getMessage().addReaction("\uD83D\uDD3C").get();
                event.getMessage().addReaction("\uD83D\uDD3D").get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ChatLog.log(event, "Reaction Added");
        }
    }
}
