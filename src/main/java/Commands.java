import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.JSONException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Commands implements MessageCreateListener {
    //Print the output from the string that the user added
    public void logSend() {
        //Initialize date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("[" + dtf.format(now) + "]" + "(LOG) Response sent!");
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        //Initialize date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();

        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        //Write to console if user sends message
        if (!event.isPrivateMessage()) {
            System.out.println("[" + dtf.format(now) + "]" + "(" +
                    event.getServer().get().getName() + " - #" +
                    event.getServerTextChannel().get().getName() + ") " +
                    event.getMessageAuthor().getName() + "#" +
                    event.getMessageAuthor().getDiscriminator().get() + ": " +
                    event.getMessage().getContent()
            );
        }

        //Custom commands
        switch (event.getMessageContent().toLowerCase()) {
            case "!shutdown":
                if (event.getMessageAuthor().getId() == Main.ownerID) {
                    event.getChannel().sendMessage("`Shutting down...`");
                    event.getApi().disconnect();
                } else {
                    event.getChannel().sendMessage("<:AlexNo:617150461127950357>");
                }
                break;
            case "!alex":
                event.getChannel().sendMessage(":regional_indicator_c::fish:");
                logSend();
                break;
            /* Eventually maybe add random stuff
            case "!releasedate":
                event.getChannel().sendMessage("[REDACTED]");
                logSend();
                break;
            */
            case "!melon":
                event.getChannel().sendMessage("||f u c k i n g  i l l e g a l||");
                logSend();
                break;
            case "!techdemo":
                event.getChannel().sendMessage("<:NotTechDemo:610603749823741963>");
                logSend();
                break;
            case "bone work bad":
                event.getChannel().type();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                event.getChannel().sendMessage("What the fuck did you just say about the greatest VR game of this century, you little crablet? I'll have you know I lead the MythOS PVP leaderboard, I've been involved in numerous Voidway duplication schemes, and I have over 300 confirmed nullbodies taken down. I am trained in VR physics warfare and I'm the top virtual grip sniper in the entirety of BONEWORKS. You are nothing to me but another crablet. I will wipe you the fuck out with the precision the likes of which has never been seen before on this dimensional plane, mark my fucking words. You think you can get away with saying that shit to us over the BONEWORKS Fan Discord? Think again, fucker. As we speak I am contacting the MythOS Police, and your positional coordinates are being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little you call your opinion. You're fucking dead, kid. I can relocate anywhere, anytime, and I can kill you with over seven hundred different physics objects, and that just in the first stage. Not only am I extensively trained in VR fists combat, but I have access to the entire arsenal of MythOS and I will use it to its full extend to unleash a fucking cryptid to wipe your miserable opinion off the face of this reality, you little shit. If only you could have know what justified anger your little 'clever' opinion was about to bring down upon you, maybe you would have held your fucking wireframe tongue. But you couldn't, you didn't, and now you're paying the price, you goddamn melon-eater. I will sever your connection to the real world and you'll remain a VR Junkie forever. You're fucking dead, kiddo.");
                logSend();
                break;
            case "blood and zombies?":
            case "blood and zombies":
                event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/617493833852256267/617638167432134656/unknown.png");
                logSend();
                break;
            case "!gameinfo":
            case "!info":
                EmbedBuilder embed = null;
                try {
                    embed = new EmbedBuilder()
                            .setTitle("BONEWORKS Info")
                            .setDescription("Info about the game")
                            //.setAuthor("Author Name", "http://google.com/", "https://cdn.discordapp.com/embed/avatars/0.png")
                            .addField("Info Source", "Info being pulled from Steam APIs")
                            .addInlineField("Release Date", JSONReader.getRelease())
                            .addInlineField("Game Price", JSONReader.getGamePrice())
                            .setColor(Color.BLUE);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //.setFooter("Footer", "https://cdn.discordapp.com/embed/avatars/1.png")
                        //.setImage(new File("C:/Users/Bastian/Pictures/puppy.jpg"))
                        //.setThumbnail(new File("C:/Users/Bastian/Pictures/kitten2.png"));
                // Send the embed
                event.getChannel().sendMessage(embed);
                logSend();
                break;
            default:
                if (event.getMessageContent().startsWith("!")) {
                    event.getChannel().sendMessage("```diff\n- " + event.getMessageContent() + " is not a valid command```");
                }
                break;
        }
    }
}