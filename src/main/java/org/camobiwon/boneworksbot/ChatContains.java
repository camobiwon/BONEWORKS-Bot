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

        if (event.getMessageAuthor().getId() == 271788139381653514) {
            try {
                event.getMessage().addReaction("🏳️‍🌈").get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

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

        if (event.getMessageContent().toLowerCase().contains("leak")) {
            try {
                event.getMessage().addReaction(":Leak:649508571247017995").get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ChatLog.log(event, "Reaction Added");
        }

        if (event.getMessageContent().toLowerCase().contains("alyx")) {
            try {
                event.getChannel().sendMessage("While I understand and respect your opinion on Half-Life: Alex, I must say that Boneworks is far superior. The latter incorporates cutting edge technology in to the virtual reality space and is much more focused on gameplay and creating believable interactions in a consistent world, HL:A is more so for those who have been waiting more of the story of Half-Life. The main showcase of this is floating hands being in HL:A and Boneworks having a full body. It's a bold move to help push VR further and innovate in the space, paving the way for future games to increase immersion. HL:A is Valve's main way to sell the Index, and yes, it may have a much more rich and grounded story than Boneworks but on a technical level the chance that the raw interactions will even come close is very slim. This is the Boneworks Discord, what the hell did you think you would get as a response?").get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ChatLog.log(event, "Message Sent");
        }
    }
}
