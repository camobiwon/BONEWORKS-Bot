package org.camobiwon.boneworksbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

class ChatLog {

    private static DiscordApi API;
    private static Long logChannel = 618142868686635090L;

    //Initialize
    static void chatInit(DiscordApi api) {
        ChatLog.API = api;
    }

    //Default log function
    static void log(MessageCreateEvent event) {
        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        //Log to console / chat
        logConsole(event);
        logCommand(event);
    }

    //Main log function
    static void log(MessageCreateEvent event, String message) {
        logConsole(event, message);
        logCommand(event, message);
    }

    //Log messaging
    static void logConsole(MessageCreateEvent event, String text) {
        //Log user command messages
        if (!event.isPrivateMessage()) {
            System.out.println("[" + Main.time() + "]" + "(" +
                    event.getServer().get().getName() + " - #" +
                    event.getServerTextChannel().get().getName() + ") " +
                    event.getMessageAuthor().getName() + "#" +
                    event.getMessageAuthor().getDiscriminator().get() + ": " +
                    //If file, grab URL, if not, send message
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

        //Log response
        if(text == null || text.isEmpty()){
            System.out.println("[" + Main.time() + "]" + "(LOG) Response Sent");
        } else {
            System.out.println("[" + Main.time() + "]" + "(LOG) " + text);
        }
    }

    //Set log message to null by default
    public static void logConsole(MessageCreateEvent event) {
        logConsole(event, null);
    }

    //Default to normal command if empty
    public static void logCommand(MessageCreateEvent event) {
        logCommand(event, "");
    }

    private static void logCommand(MessageCreateEvent event, String message) {
        //If bot, don't respond
        if (event.getMessageAuthor().isBotUser()) return;

        //Default message
        if (message == null || message.isEmpty()) {
            message = "Response Sent";
        }

        //Grab user message
        if (!event.isPrivateMessage()) {
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("#" + event.getServerTextChannel().get().getName())
                    .setDescription(Main.time() + " | PST")
                    .setAuthor(event.getMessageAuthor().getName() + "#" + event.getMessageAuthor().getDiscriminator().get(), "", event.getMessageAuthor().getAvatar())
                    .setColor(Color.GRAY)
                    .setFooter(event.getMessageContent(), "");
            event.getApi().getTextChannelById(logChannel).get().sendMessage(embed);
        }

        //Bot response message
        if (!event.isPrivateMessage()) {
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("#" + event.getServerTextChannel().get().getName())
                    .setDescription(Main.time() + " | PST")
                    .setAuthor("Bot Response", "", API.getYourself().getAvatar())
                    .setColor(Color.BLUE)
                    .setFooter(message, "");
            event.getApi().getTextChannelById(logChannel).get().sendMessage(embed);
        }
    }

    //Respond in Discord chat with logged message
    static void logMessage(String message) {

        //Return if empty
        if (message == null || message.isEmpty()) {
            return;
        }

        //Bot log messages
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(message)
                .setDescription(Main.time() + " | PST")
                .setAuthor("Bot Log", "", API.getYourself().getAvatar())
                .setColor(Color.ORANGE);
        Main.getServer().getTextChannelById(logChannel).get().sendMessage(embed);
    }
}