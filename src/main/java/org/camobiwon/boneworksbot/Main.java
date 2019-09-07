package org.camobiwon.boneworksbot;

import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
    private static Logger logger = LogManager.getLogger(Main.class);
    static List<Long> adminIDs = new ArrayList<>();
    private static DiscordApi api;
    private static ResourceLoader resourceLoader;

    //Time formatting
    static String time() {
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //Get admins from admin.txt file
    private static void getAdmins() {
        String admins;
        try {
            admins = resourceLoader.getFileContent("admins.txt");
            String[] admin = admins.split(",");
            for (String parseLong: admin) {
                adminIDs.add(Long.parseLong(parseLong));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("admins.txt file not found / unreadable! (Make sure it's in src\\main\\resources)");
        }
    }

    //Get server ID
    static Server getServer() {
        long serverID = 563139253542846474L;
        return api.getServerById(serverID).get();
    }

    //Get owner ID
    static Long getOwner() {
        return api.getOwnerId();
    }

    //Main call
    public static void main(String[] args) {
        resourceLoader = new ResourceLoader();

        try {
            //Get token from token.txt file
            System.out.println("Reading token from file...");
            String token = resourceLoader.getFileContent("token.txt");
            System.out.println("Token set\n");

            //Grab admin list
            System.out.println("Reading admin list from file...");
            getAdmins();
            System.out.println("Got admin list\n");

            //Go online
            System.out.println("Logging into bot...");
            api = new DiscordApiBuilder().setToken(token).login().join();
            System.out.println("Logged into bot\n");

            //Initialize
            System.out.println("Initializing logging...");
            ChatLog.chatInit(api);
            FallbackLoggerConfiguration.setDebug(true);
            System.out.println("Logging initialized\n");

            //Listeners
            System.out.println("Setting listeners...");
            api.addMessageCreateListener(new OldCommands());
            api.addMessageCreateListener(new ChatContains());
            //CommandHandler cmdHandler = new JavacordHandler(api);
            //cmdHandler.registerCommand(new Commands());
            System.out.println("Listeners set\n");

            //Online messages
            ChatLog.logMessage("Bot Online");
            System.out.println("Startup successful! Bot should now be online\n");
            System.out.println("=====[ CONSOLE OUTPUT ]=====\n");

            //Log output if bot joins / leaves server
            api.addServerJoinListener(event -> logger.info("Joined server " + event.getServer().getName()));
            api.addServerLeaveListener(event -> logger.info("Left server " + event.getServer().getName()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("token.txt file not found / unreadable! (Make sure it's in src\\main\\resources)");
        }
    }
}