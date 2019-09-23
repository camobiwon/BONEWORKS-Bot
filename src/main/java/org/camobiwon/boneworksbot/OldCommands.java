package org.camobiwon.boneworksbot;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.JSONException;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class OldCommands implements MessageCreateListener {

    //Setup fake key generator
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    String canChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        //Log DMs
        if (event.isPrivateMessage()) {
            System.out.println("[" + Main.time() + "]" +
                    "(Direct Message)" +
                    event.getMessageAuthor().getName() + "#" +
                    event.getMessageAuthor().getDiscriminator().get() + ": " +
                    event.getMessage().getContent()
            );
        }

        //Custom commands
        switch (event.getMessageContent().toLowerCase()) {

            //Admin commands
            case "!restart":
                if(CommandUtils.isAdmin(event)) {
                    event.getChannel().sendMessage("`Restarting Bot...`");
                    ChatLog.logConsole(event, "Bot Restarting");
                    ChatLog.logMessage("Bot Restarting");
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
            case "alex":
                event.getChannel().sendMessage(":regional_indicator_c::fish:");
                ChatLog.log(event);
                break;

            case "releasedate":
                try {
                    event.getChannel().sendMessage("The current planned release date is: " + JSONReader.getRelease());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                ChatLog.log(event);
                break;
            
            case "melon":
                event.getChannel().sendMessage("||f u c k i n g  i l l e g a l||");
                ChatLog.log(event);
                break;

            case "bonework good":
                //Key generator
                String genKey = "";
                for (int i = 0; i < 5; i++) {
                    if(i > 0) {
                        genKey += sb;
                        genKey += "-";
                    }
                    sb.delete(0, 5);
                    for (int ii = 0; ii < 5; ii++) {
                        sb.append(canChars.charAt(random.nextInt(canChars.length())));
                    }
                }
                genKey += sb;
                event.getChannel().sendMessage("Glad we can all agree on something, here's a key for the game: " + genKey);
                ChatLog.log(event);
                break;

            case "bonework bad":
                event.getChannel().sendMessage("What the fuck did you just say about the greatest VR game of this century, you little crablet? I'll have you know I lead the MythOS PVP leaderboard, I've been involved in numerous Voidway duplication schemes, and I have over 300 confirmed nullbodies taken down. I am trained in VR physics warfare and I'm the top virtual grip sniper in the entirety of BONEWORKS. You are nothing to me but another crablet. I will wipe you the fuck out with the precision the likes of which has never been seen before on this dimensional plane, mark my fucking words. You think you can get away with saying that shit to us over the BONEWORKS Fan Discord? Think again, fucker. As we speak I am contacting the MythOS Police, and your positional coordinates are being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little you call your opinion. You're fucking dead, kid. I can relocate anywhere, anytime, and I can kill you with over seven hundred different physics objects, and that just in the first stage. Not only am I extensively trained in VR fists combat, but I have access to the entire arsenal of MythOS and I will use it to its full extend to unleash a fucking cryptid to wipe your miserable opinion off the face of this reality, you little shit. If only you could have know what justified anger your little 'clever' opinion was about to bring down upon you, maybe you would have held your fucking wireframe tongue. But you couldn't, you didn't, and now you're paying the price, you goddamn melon-eater. I will sever your connection to the real world and you'll remain a VR Junkie forever. You're fucking dead, kiddo.");
                ChatLog.log(event);
                break;

            case "blood and zombies":
                event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/617493833852256267/617638167432134656/unknown.png");
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
        }
    }
}