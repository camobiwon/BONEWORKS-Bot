package org.camobiwon.boneworksbot;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.JSONException;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Commands implements MessageCreateListener {

    //Check if user is admin
    private boolean isAdmin(MessageCreateEvent event) {
        boolean isAdmin = Main.adminIDs.contains((event.getMessageAuthor().getId()));
        if (!isAdmin) {
            event.getChannel().sendMessage("<:AlexNo:617150461127950357>");
            ChatLog.log(event);
        }
        return isAdmin;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        //Log user sent messages
        if (!event.isPrivateMessage()) {
            System.out.println("[" + Main.time() + "]" + "(" +
                    event.getServer().get().getName() + " - #" +
                    event.getServerTextChannel().get().getName() + ") " +
                    event.getMessageAuthor().getName() + "#" +
                    event.getMessageAuthor().getDiscriminator().get() + ": " +
                    //If image, grab URL, if not, send message
                    (event.getMessageAttachments().isEmpty()?event.getMessage().getContent():"<FILE> " + event.getMessageAttachments().get(0).getUrl())
            );
        } else {
            System.out.println("[" + Main.time() + "]" +
                    "(Direct Message)" +
                    event.getMessageAuthor().getName() + "#" +
                    event.getMessageAuthor().getDiscriminator().get() + ": " +
                    event.getMessage().getContent()
            );
        }

        //Contains
        String messageContent = event.getMessageContent().toLowerCase();
        if (messageContent.contains("bone work") || messageContent.contains("bonework")) {
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


        //Custom commands
        switch (event.getMessageContent().toLowerCase()) {
            //Admin commands
            case "!shutdown":
                if(isAdmin(event)) {
                    event.getChannel().sendMessage("`Shutting Down...`");
                    ChatLog.logConsole("Bot Shutting Down");
                    ChatLog.logMessage("Bot Shutting Down");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    event.getApi().disconnect();

                    try {
                        Thread.sleep(5000);
                        System.exit(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;


            //Standard commands
            case "!alex":
                event.getChannel().sendMessage(":regional_indicator_c::fish:");
                ChatLog.log(event);
                break;

            // Eventually add randomized dates, but for now, have it be the actual release date or something
            case "!releasedate":
                try {
                    event.getChannel().sendMessage("The current planned release date is: " + JSONReader.getRelease());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                ChatLog.log(event);

                break;
            
            case "!melon":
                event.getChannel().sendMessage("||f u c k i n g  i l l e g a l||");
                ChatLog.log(event);
                break;

            case "bonework bad":
                event.getChannel().sendMessage("What the fuck did you just say about the greatest VR game of this century, you little crablet? I'll have you know I lead the MythOS PVP leaderboard, I've been involved in numerous Voidway duplication schemes, and I have over 300 confirmed nullbodies taken down. I am trained in VR physics warfare and I'm the top virtual grip sniper in the entirety of BONEWORKS. You are nothing to me but another crablet. I will wipe you the fuck out with the precision the likes of which has never been seen before on this dimensional plane, mark my fucking words. You think you can get away with saying that shit to us over the BONEWORKS Fan Discord? Think again, fucker. As we speak I am contacting the MythOS Police, and your positional coordinates are being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little you call your opinion. You're fucking dead, kid. I can relocate anywhere, anytime, and I can kill you with over seven hundred different physics objects, and that just in the first stage. Not only am I extensively trained in VR fists combat, but I have access to the entire arsenal of MythOS and I will use it to its full extend to unleash a fucking cryptid to wipe your miserable opinion off the face of this reality, you little shit. If only you could have know what justified anger your little 'clever' opinion was about to bring down upon you, maybe you would have held your fucking wireframe tongue. But you couldn't, you didn't, and now you're paying the price, you goddamn melon-eater. I will sever your connection to the real world and you'll remain a VR Junkie forever. You're fucking dead, kiddo.");
                ChatLog.log(event);
                break;

            case "blood and zombies?":
            case "blood and zombies":
                event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/617493833852256267/617638167432134656/unknown.png");
                ChatLog.log(event);
                break;

            case "!adamdev":
                event.getChannel().sendMessage("method(method)");
                ChatLog.log(event);
                break;

            case "!gameinfo":
            case "!info":
                EmbedBuilder embed = null;
                try {
                    embed = new EmbedBuilder()
                            .setTitle("BONEWORKS Info")
                            .setDescription("Info about the game")
                            .addField("Info Source", "Info being pulled from Steam game data")
                            .addInlineField("Release Date", JSONReader.getRelease())
                            .addInlineField("Game Price", JSONReader.getGamePrice())
                            .setColor(Color.BLUE);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                event.getChannel().sendMessage(embed);
                ChatLog.log(event);
                break;

            default:
                if (event.getMessageContent().startsWith("!")) {
                    event.getChannel().sendMessage("```diff\n- " + event.getMessageContent() + " is not a valid command```");
                    ChatLog.log(event, "Fail Response Sent");
                }
                break;
        }
    }
}